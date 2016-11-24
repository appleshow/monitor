package com.aps.monitor.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HbDataModeMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbDataMode;
import com.aps.monitor.model.HbDataTable;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbDataContrastService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: HbDataContrastServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月24日 下午9:03:54
 * 
 * @since 1.0.0
 */
@Service
public class HbDataContrastServiceImpl implements IHbDataContrastService {
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
	 * Title: referHbType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbDataContrastService#referHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbType(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems;

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		responseData.setData(hbTypes);

		ObjectNode subJoin = JsonUtil.getObjectNodeInstance();
		subJoin.putArray("typeItems").addAll(JsonUtil.valueToArrayNode(hbTypeItems));
		responseData.setSubJoinJson(subJoin);
	}

	/**
	 * 
	 * <p>
	 * Title: referHbNode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbDataContrastService#referHbNode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbNode(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbNode hbNode = new HbNode();
		List<HbNode> hbNodes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		hbNode.setTypeId(requestRefPar.getIntegerPar("typeId"));
		hbNode.setNodeId(requestRefPar.getIntegerPar("nodeId"));
		hbNodes = hbNodeMapper.selectByCondition(hbNode);

		responseData.setData(hbNodes);
	}

	/**
	 * 
	 * <p>
	 * Title: refHbData
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @throws ParseException
	 * @see com.aps.monitor.service.IHbDataContrastService#refHbData(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbData(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes = new ArrayList<>(), hbDataModesTmp;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageInfo<HbDataMode> pageInfo;
		String[] nodeIds = requestRefPar.getStringPar("nodeId").split(";");
		String[] nodeMns = requestRefPar.getStringPar("nodeMn").split(";");
		int totalCount = 0;

		for (int index = 0; index < nodeIds.length; index++) {
			hbDataTable.setNodeId(Integer.parseInt(nodeIds[index]));
			hbDataTable.setNodeMn(nodeMns[index]);
			hbDataTable.setDataType(requestRefPar.getStringPar("dataType"));
			hbDataTable.setDateStr(dateFormat.parse(requestRefPar.getStringPar("dateStr")));
			hbDataTable.setDateEnd(dateFormat.parse(requestRefPar.getStringPar("dateEnd")));
			hbDataTable.setProperty0("hb_data_cur" + nodeIds[index]);
			PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize") / nodeIds.length);
			hbDataModesTmp = hbDataModeMapper.selectByCondition(hbDataTable);
			pageInfo = new PageInfo<HbDataMode>(hbDataModesTmp);
			totalCount += pageInfo.getTotal();
			hbDataModes.addAll(hbDataModesTmp);
		}
		responseData.setTotalCount(totalCount);
		responseData.setData(hbDataModes.stream().sorted((hbDataMode1, hbDataMode2) -> hbDataMode1.getDataTime().before(hbDataMode2.getDataTime()) ? -1 : 1)
				.collect(Collectors.toList()));
	}

}
