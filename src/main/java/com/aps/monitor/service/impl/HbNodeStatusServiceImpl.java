package com.aps.monitor.service.impl;

import java.io.IOException;
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
import com.aps.monitor.comm.StringUtil;
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
import com.fasterxml.jackson.databind.node.ArrayNode;
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
	 * 
	 * @Title: findParName
	 * @Description: TODO
	 * @param hbTypeItems
	 * @param hbNode
	 * @param par
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	private String findParName(List<HbTypeItem> hbTypeItems, HbNode hbNode, String par) {
		Optional<HbTypeItem> hbTypeItemFind = hbTypeItems.stream().filter(typeItem -> {
			return typeItem.getTypeId() == hbNode.getTypeId() && typeItem.getItemId().equals(par);
		}).findFirst();
		if (hbTypeItemFind.isPresent()) {
			return hbTypeItemFind.get().getItemName();
		} else {
			return par;
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
		String nodeStatus = "<kbd style='background:red'>离线</kbd>";
		if (!StringUtil.isNullOrEmpty(hbNod.getProperty9())) {
			try {
				int offline = 5;
				ObjectNode nodeAtr = JsonUtil.getObjectMapper().readValue(hbNod.getNodeAtr(), ObjectNode.class);
				Date checkDate = DateUtil.fromString(hbNod.getProperty9(), DateUtil.SIMPLE_DATE_FORMAT1);
				Date nowDate = new Date();
				if (nodeAtr.has("offline")) {
					offline = nodeAtr.get("offline").asInt();
				}
				if ((nowDate.getTime() - checkDate.getTime()) / (1000 * 60) <= offline) {
					nodeStatus = "<kbd style='background:green'>在线</kbd>";
				}
			} catch (Exception e) {
			}
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
		final Date nowDate = new Date();
		final List<String> dataInfo = new ArrayList<>();
		final HbDataTable hbDataTable = new HbDataTable();
		final List<HbDataMode> hbDataModes;
		final ArrayNode nodeParValeInfo = JsonUtil.getArrayNodeInstance();

		try {
			hbDataTable.setNodeId(hbNode.getNodeId());
			hbDataTable.setNodeMn(hbNode.getNodeMn());
			hbDataTable.setDataType("2011");
			hbDataTable.setDateStr(new Date(nowDate.getTime() - 1000 * 60 * 60 * hours));
			hbDataTable.setDateEnd(nowDate);
			hbDataTable.setProperty0("hb_data_cur" + hbNode.getNodeId());
			hbDataModes = hbDataModeMapper.selectByCondition(hbDataTable);

			if (hbDataModes != null && hbDataModes.size() > 0) {
				final int totalDataCount = hbDataModes.size();
				final ObjectNode nodeItems = JsonUtil.getObjectMapper().readValue(hbNode.getNodeItem(), ObjectNode.class);
				final ArrayNode nodeDatas = JsonUtil.getArrayNodeInstance();
				hbDataModes.stream().forEach(dataMode -> {
					if (!StringUtil.isNullOrEmpty(dataMode.getNodeData())) {
						try {
							nodeDatas.add(JsonUtil.getObjectMapper().readValue(dataMode.getNodeData(), ObjectNode.class));
						} catch (IOException e) {
						}
					}
				});
				nodeItems.fieldNames().forEachRemaining(parName -> {
					final JsonNode parInfo = nodeItems.get(parName);
					if (parInfo.get("select").asInt() == 1) {
						//Find min and max value from setting
						final float minValue, maxValue;
						if (!StringUtil.isNullOrEmpty(parInfo.get("itemVmin").asText())) {
							minValue = (float) parInfo.get("itemVmin").asDouble();
						} else {
							minValue = Float.MIN_VALUE;
						}
						if (!StringUtil.isNullOrEmpty(parInfo.get("itemVmax").asText())) {
							maxValue = (float) parInfo.get("itemVmax").asDouble();
						} else {
							maxValue = Float.MAX_VALUE;
						}
						//Check each row
						final ObjectNode checkParInfo = JsonUtil.getObjectNodeInstance();
						checkParInfo.put("name", findParName(hbTypeItems, hbNode, parName));
						checkParInfo.put("min", 0);
						checkParInfo.put("max", 0);
						nodeDatas.forEach(nodeData -> {
							if (nodeData.has(parName)) {
								float parValue = (float) nodeData.get(parName).asDouble();
								if (parValue < minValue) {
									checkParInfo.put("min", checkParInfo.get("min").asInt() + 1);
								} else if (parValue > maxValue) {
									checkParInfo.put("max", checkParInfo.get("max").asInt() + 1);
								}
							}
						});

						if (checkParInfo.get("min").asInt() > 0 || checkParInfo.get("max").asInt() > 0) {
							nodeParValeInfo.add(checkParInfo);
						}
					}
				});

				dataInfo.add(DateUtil.formatString(hbDataModes.get(hbDataModes.size() - 1).getItime(), DateUtil.SIMPLE_DATE_FORMAT1));
				dataInfo.add("");
				nodeParValeInfo.forEach(nodeParValueInfoTmp -> {
					int minCount = nodeParValueInfoTmp.get("min").asInt();
					int maxCount = nodeParValueInfoTmp.get("max").asInt();
					if (minCount > 0 && maxCount > 0) {
						dataInfo.set(1, dataInfo.get(1) + String.format(" <kbd style='background:purple' title='%d条记录中，超出下限: %d条;超出上限: %d条'>%s</kbd>",
								totalDataCount, minCount, maxCount, nodeParValueInfoTmp.get("name").asText()));
					} else if (minCount > 0) {
						dataInfo.set(1, dataInfo.get(1) + String.format(" <kbd style='background:green' title='%d条记录中，超出下限: %d条'>%s</kbd>", totalDataCount,
								minCount, nodeParValueInfoTmp.get("name").asText()));
					} else if (maxCount > 0) {
						dataInfo.set(1, dataInfo.get(1) + String.format(" <kbd style='background:red' title='%d条记录中，超出上限: %d条'>%s</kbd>", totalDataCount,
								maxCount, nodeParValueInfoTmp.get("name").asText()));
					}
				});
			} else {
				dataInfo.add("");
				dataInfo.add("");
			}
		} catch (IOException e) {
			dataInfo.add("");
			dataInfo.add("");
		}

		return dataInfo;
	}
}
