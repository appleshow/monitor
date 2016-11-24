package com.aps.monitor.service.impl;

import java.io.IOException;
import java.text.ParseException;
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
import com.aps.monitor.service.IHbDataStatisticsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @ClassName: HbDataStatisticsServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月24日 下午9:41:01
 * 
 * @since 1.0.0
 */
@Service
public class HbDataStatisticsServiceImpl implements IHbDataStatisticsService {
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
	 * @see com.aps.monitor.service.IHbDataStatisticsService#refHbData(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbData(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException {
		final RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
		final Date dateFrom = DateUtil.fromString(requestRefPar.getStringPar("dateStr"), DateUtil.SIMPLE_DATE_FORMAT1);
		final Date dateTo = DateUtil.fromString(requestRefPar.getStringPar("dateEnd"), DateUtil.SIMPLE_DATE_FORMAT1);
		final HbNode hbNode = CommUtil.getHbNodeCache().get(CommUtil.getHbNodeCacheMNfromID(requestRefPar.getIntegerPar("nodeId")));
		final HbTypeItem hbTypeItem = new HbTypeItem();
		final List<HbTypeItem> hbTypeItems;
		final List<ObjectNode> nodeParInfo;

		hbTypeItem.setTypeId(hbNode.getTypeId());
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);

		nodeParInfo = findNodeDataInfo(hbTypeItems, hbNode, requestRefPar.getStringPar("dataType"), dateFrom, dateTo);

		responseData.setTotalCount(nodeParInfo.size());
		responseData.setData(nodeParInfo);
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
	 * 查询数据相关信息
	 * 
	 * @Title: findNodeDataInfo
	 * @Description: TODO
	 * @param hbTypeItems
	 * @param hbNode
	 * @param dataType
	 * @param fromDate
	 * @param toDate
	 * @return List<ObjectNode>
	 * @throws:
	 * @since 1.0.0
	 */
	private List<ObjectNode> findNodeDataInfo(List<HbTypeItem> hbTypeItems, HbNode hbNode, String dataType, Date dateFrom, Date dateTo) {
		final HbDataTable hbDataTable = new HbDataTable();
		final List<HbDataMode> hbDataModes;
		final List<ObjectNode> nodeParValeInfo = new ArrayList<>();

		try {
			hbDataTable.setNodeId(hbNode.getNodeId());
			hbDataTable.setNodeMn(hbNode.getNodeMn());
			hbDataTable.setDataType(dataType);
			hbDataTable.setDateStr(dateFrom);
			hbDataTable.setDateEnd(dateTo);
			hbDataTable.setProperty0("hb_data_cur" + hbNode.getNodeId());
			hbDataModes = hbDataModeMapper.selectByCondition(hbDataTable);

			if (hbDataModes != null && hbDataModes.size() > 0) {
				final int totalDataCount = hbDataModes.size();
				final ObjectNode nodeItems = JsonUtil.getObjectMapper().readValue(hbNode.getNodeItem(), ObjectNode.class);
				final ArrayNode nodeDatas = JsonUtil.getArrayNodeInstance();
				final ObjectNode checkParInfoTmp = JsonUtil.getObjectNodeInstance();
				checkParInfoTmp.put("itemId", hbNode.getNodeId());
				checkParInfoTmp.put("itemName", hbNode.getNodeName());
				checkParInfoTmp.put("unit", "");
				checkParInfoTmp.put("minValue", "");
				checkParInfoTmp.put("maxValue", "");
				checkParInfoTmp.put("dataCount", totalDataCount);
				checkParInfoTmp.put("min", "");
				checkParInfoTmp.put("max", "");
				checkParInfoTmp.put("avg", "");
				checkParInfoTmp.put("minCount", "");
				checkParInfoTmp.put("maxCount", "");
				checkParInfoTmp.put("offlineCount", "");
				checkParInfoTmp.put("offlineLength", "");
				checkParInfoTmp.put("offlineLast", "");
				nodeParValeInfo.add(checkParInfoTmp);

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
						checkParInfo.put("itemId", parName);
						checkParInfo.put("itemName", findParName(hbTypeItems, hbNode, parName));
						checkParInfo.put("unit", parInfo.get("itemUnit").asText());
						checkParInfo.put("minValue", parInfo.get("itemVmin").asText());
						checkParInfo.put("maxValue", parInfo.get("itemVmax").asText());
						checkParInfo.put("dataCount", 0);
						checkParInfo.put("min", Float.MAX_VALUE);
						checkParInfo.put("max", Float.MIN_VALUE);
						checkParInfo.put("avg", 0);
						checkParInfo.put("minCount", 0);
						checkParInfo.put("maxCount", 0);
						nodeDatas.forEach(nodeData -> {
							if (nodeData.has(parName)) {
								float parValue = (float) nodeData.get(parName).asDouble();

								checkParInfo.put("dataCount", checkParInfo.get("dataCount").asInt() + 1);
								if (parValue < checkParInfo.get("min").asDouble()) {
									checkParInfo.put("min", parValue);
								}
								if (parValue > checkParInfo.get("max").asDouble()) {
									checkParInfo.put("max", parValue);
								}
								checkParInfo.put("avg", checkParInfo.get("avg").asDouble() + parValue);
								if (parValue < minValue) {
									checkParInfo.put("minCount", checkParInfo.get("minCount").asInt() + 1);
								} else if (parValue > maxValue) {
									checkParInfo.put("maxCount", checkParInfo.get("maxCount").asInt() + 1);
								}
							}
						});

						if (checkParInfo.get("dataCount").asInt() > 0) {
							checkParInfo.put("avg", Math.round((checkParInfo.get("avg").asDouble() / checkParInfo.get("dataCount").asInt()) * 1000) / 1000.0);
							if (checkParInfo.get("minCount").asInt() > 0) {
								checkParInfo.put("minCount", String.format(" <kbd style='background:green'>%s</kbd>", checkParInfo.get("minCount").asText()));
							} else {
								checkParInfo.put("minCount", "");
							}
							if (checkParInfo.get("maxCount").asInt() > 0) {
								checkParInfo.put("maxCount", String.format(" <kbd style='background:red'>%s</kbd>", checkParInfo.get("maxCount").asText()));
							} else {
								checkParInfo.put("maxCount", "");
							}
						} else {
							checkParInfo.put("min", "");
							checkParInfo.put("max", "");
							checkParInfo.put("avg", "");
							checkParInfo.put("minCount", "");
							checkParInfo.put("maxCount", "");
						}

						nodeParValeInfo.add(checkParInfo);
					}
				});
			} else {
			}
		} catch (IOException e) {
		}

		return nodeParValeInfo;
	}
}
