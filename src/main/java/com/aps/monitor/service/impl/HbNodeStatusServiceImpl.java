package com.aps.monitor.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.DateUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HBDataLatestMapper;
import com.aps.monitor.dao.HbDataModeMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbDataMode;
import com.aps.monitor.model.HbDataTable;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbNodeStatusService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 站点状态服务层
 * 
 * @ClassName: HbNodeStatusServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月22日 下午9:35:53
 * 
 * @since 1.0.0
 */
@Service
public class HbNodeStatusServiceImpl implements IHbNodeStatusService {
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbTypeItemMapper hbTypeItemMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;
	@Resource
	private HbDataModeMapper hbDataModeMapper;

	/**
	 * 
	 * <p>
	 * Title: refNodeStatus
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbNodeStatusService#refNodeStatus(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refNodeStatus(HttpSession httpSession, String inPar, ResponseData responseData) {
		final List<ObjectNode> objectNodes = new ArrayList<>();
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
		//查询所有的类型
		HbType hbType = new HbType();
		List<HbType> hbTypes = hbTypeMapper.selectByCondition(hbType);
		//查询所有类型和参数
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		CommUtil.getHbNodeCache().forEach((nodeMn, hbNodeTmp) -> {
			ObjectNode objectNode = JsonUtil.getObjectNodeInstance();

			objectNode.put("nodeId", hbNodeTmp.getNodeId());
			objectNode.put("nodeMn", hbNodeTmp.getNodeMn());
			objectNode.put("nodeName", hbNodeTmp.getNodeName());
			objectNode.put("typeName", findNodeTypeName(hbTypes, hbNodeTmp.getTypeId()));
			objectNode.put("nodeStatus", findNodeStatus(hbNodeTmp));
			objectNode.put("dataFrom", hbNodeTmp.getUfrom());
			objectNode.put("receiveTime", hbNodeTmp.getProperty9());

			List<String> dataInfo = findNodeDataInfo(hbTypeItems, hbNodeTmp, requestRefPar.getIntegerPar("hours"));
			objectNode.put("latestTime", dataInfo.get(0));
			objectNode.put("exceptionPars", dataInfo.get(1));

			objectNodes.add(objectNode);

		});

		responseData.setTotalCount(objectNodes.size());
		responseData.setData(objectNodes);
	}

	/**
	 * 查询站点类型名称
	 * 
	 * @Title: findNodeTypeName
	 * @Description: TODO
	 * @param hbTypes
	 * @param typeId
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	private String findNodeTypeName(List<HbType> hbTypes, int typeId) {
		Optional<HbType> hbType = hbTypes.stream().filter(type -> typeId == type.getTypeId()).findFirst();
		if (hbType.isPresent()) {
			return hbType.get().getTypeName();
		} else {
			return "";
		}
	}

	/**
	 * 查询站点是否在线
	 * 
	 * @Title: findNodeStatus
	 * @Description: TODO
	 * @param hbNodes
	 * @param hbNod
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	private String findNodeStatus(HbNode hbNod) {
		String nodeStatus = "离线";

		try {
			int offline = 5;
			ObjectNode nodeAtr = JsonUtil.getObjectMapper().readValue(hbNod.getNodeAtr(), ObjectNode.class);
			Date checkDate = DateUtil.fromString(hbNod.getProperty9(), DateUtil.SIMPLE_DATE_FORMAT1);
			Date nowDate = new Date();
			if (nodeAtr.has("offline")) {
				offline = nodeAtr.get("offline").asInt();
			}
			if ((nowDate.getTime() - checkDate.getTime()) / (1000 * 60) <= offline) {
				nodeStatus = "在线";
			}
		} catch (IOException e) {
		}

		return nodeStatus;
	}

	/**
	 * 查询数据相关信息
	 * 
	 * @Title: findNodeDataInfo
	 * @Description: TODO
	 * @param hbNode
	 * @return List<String>
	 * @throws:
	 * @since 1.0.0
	 */
	private List<String> findNodeDataInfo(List<HbTypeItem> hbTypeItems, HbNode hbNode, int hours) {
		Date nowDate = new Date();
		List<String> dataInfo = new ArrayList<>();
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes;

		try {
			ObjectNode nodeItems = JsonUtil.getObjectMapper().readValue(hbNode.getNodeItem(), ObjectNode.class);

			for (JsonNode nodeItem : nodeItems) {
			}
			hbDataTable.setNodeId(hbNode.getNodeId());
			hbDataTable.setNodeMn(hbNode.getNodeMn());
			hbDataTable.setDataType("2011");
			hbDataTable.setDateStr(new Date(nowDate.getTime() + 1000 * 60 * 60 * hours));
			hbDataTable.setDateEnd(nowDate);
			hbDataTable.setProperty0("hb_data_cur" + hbNode.getNodeId());
			hbDataModes = hbDataModeMapper.selectByCondition(hbDataTable);
		} catch (IOException e) {
		}

		return dataInfo;
	}
}
