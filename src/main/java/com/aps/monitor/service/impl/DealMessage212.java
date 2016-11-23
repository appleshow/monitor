package com.aps.monitor.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aps.monitor.cache.CacheKey;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.DateUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.KeyValue;
import com.aps.monitor.dao.HBDataLatestMapper;
import com.aps.monitor.dao.HbDataModeMapper;
import com.aps.monitor.dao.HbDataRecordMapper;
import com.aps.monitor.data.Message;
import com.aps.monitor.model.HBDataLatest;
import com.aps.monitor.model.HBDataLatestKey;
import com.aps.monitor.model.HbDataMode;
import com.aps.monitor.model.HbDataRecord;
import com.aps.monitor.service.IDealMessage;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @ClassName: DealMessage212
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月2日 下午9:45:21
 * 
 * @since 1.0.0
 */
@Service
public class DealMessage212 implements IDealMessage {
	@Resource
	private HbDataRecordMapper hbDataRecordMapper;
	@Resource
	private HbDataModeMapper hbDataModeMapper;
	@Resource
	private HBDataLatestMapper hbDataLatestMapper;
	private static final Logger LOG = LogManager.getLogger(DealMessage212.class);

	/**
	 * 
	 * <p>
	 * Title: saveMessage
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param cacheKey
	 * @param message
	 * @return
	 * @see com.aps.monitor.service.IDealMessage#saveMessage(com.aps.monitor.cache.CacheKey,
	 *      com.aps.monitor.data.Message)
	 */
	@Override
	public boolean saveMessage(CacheKey cacheKey, Message message) {
		return insertMessage(cacheKey, message);
	}

	/**
	 * 
	 * @Title: insertIntoHbDataMode
	 * @Description: TODO
	 * @param hbDataRecord
	 * @param nowDate
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	private boolean insertMessage(CacheKey cacheKey, Message message) {
		final HbDataMode hbDataMode = new HbDataMode();
		final HbDataRecord hbDataRecord = new HbDataRecord();
		Date nowDate = new Date();
		String recordId = String.valueOf(System.currentTimeMillis());
		String nodeData = message.getMessageBodyTailor();

		hbDataRecord.setRecordId(recordId);
		hbDataRecord.setRecordData(nodeData);
		hbDataRecord.setPrflag(0);
		hbDataRecord.setUfrom(message.getFromHost());
		hbDataRecord.setItime(nowDate);
		hbDataRecord.setUtime(nowDate);
		if (nodeData.length() > 2 && nodeData.startsWith("##") && nodeData.indexOf("&&") > 0 && nodeData.indexOf("DataTime=") > 0) {
			List<String[]> dataList = Arrays.asList(nodeData.split("&&")).stream().map(item -> item.split(";")).collect(Collectors.toList());
			if (dataList.size() >= 2) {
				Arrays.asList(dataList.get(0)).stream().filter(item -> item.startsWith("MN=")).findFirst().ifPresent(item -> {
					format2KeyValue(item).ifPresent(keyValue -> hbDataMode.setNodeMn(keyValue.getValue()));
				});
				if (CommUtil.getHbNodeCache().containsKey(hbDataMode.getNodeMn())) {
					CommUtil.getHbNodeCache().get(hbDataMode.getNodeMn()).setUfrom(hbDataRecord.getUfrom());
					CommUtil.getHbNodeCache().get(hbDataMode.getNodeMn()).setProperty9(message.getReceiveDate());;
					Arrays.asList(dataList.get(0)).stream().filter(item -> item.startsWith("CN=")).findFirst().ifPresent(item -> {
						format2KeyValue(item).ifPresent(keyValue -> hbDataMode.setDataType(keyValue.getValue()));
					});
					Arrays.asList(dataList.get(1)).stream().filter(item -> item.startsWith("DataTime=")).findFirst().ifPresent(item -> {
						format2KeyValue(item)
								.ifPresent(keyValue -> hbDataMode.setDataTime(DateUtil.fromString(keyValue.getValue(), DateUtil.SIMPLE_DATE_FORMAT2)));
					});
					final ObjectNode jsonPar = JsonUtil.getObjectNodeInstance();
					for (String item : dataList.get(1)) {
						if (item.indexOf(",") > 0) {
							Arrays.asList(item.split(",")).stream().forEach(par -> {
								format2KeyValue(par).ifPresent(keyValue -> {
									jsonPar.put(keyValue.getKey(), keyValue.getValue());
								});
							});
						} else if (!item.startsWith("DataTime=")) {
							format2KeyValue(item).ifPresent(keyValue -> {
								jsonPar.put(keyValue.getKey(), keyValue.getValue());
							});

						}
					}
					if (jsonPar.size() > 0) {
						hbDataRecord.setPrflag(1);
						switch (hbDataMode.getDataType()) {
							case "2011"://实时数据
								hbDataMode.setNodeData(jsonPar.toString().replace(CommUtil.HB_DATA_RTD212, ""));
								break;
							case "2051"://分钟数据（5分钟或10分钟）
								hbDataMode.setNodeData(jsonPar.toString().replace(CommUtil.HB_DATA_AVG212, ""));
								break;
							case "2061"://小时数据
								hbDataMode.setNodeData(jsonPar.toString().replace(CommUtil.HB_DATA_AVG212, ""));
								break;
							default:
								hbDataMode.setNodeData(jsonPar.toString());
								break;
						}
						hbDataMode.setProperty0(CommUtil.HB_DATA_CUR + CommUtil.getHbNodeCache().get(hbDataMode.getNodeMn()).getNodeId());
						hbDataMode.setUfrom(recordId);
						hbDataMode.setItime(nowDate);
						hbDataMode.setUtime(nowDate);
					} else {
						hbDataRecord.setPrexp("Can not find item data!");
					}
				} else {
					hbDataRecord.setPrexp("Can not find MN: [" + hbDataMode.getNodeMn() + "]!");
				}
			} else {
				hbDataRecord.setPrexp("Can not format data using DealMessage212,can not find key word [;]!");
			}
		} else {
			hbDataRecord.setPrexp("Can not format data using DealMessage212!");
		}

		if (hbDataRecord.getPrflag() == 1) {
			try {
				hbDataModeMapper.insertSelective(hbDataMode);
				hbDataMode.setProperty0(CommUtil.HB_DATA_HIS + CommUtil.getHbNodeCache().get(hbDataMode.getNodeMn()).getNodeId());
				hbDataModeMapper.insertSelective(hbDataMode);

				if ("2011".equals(hbDataMode.getDataType())) {
					HBDataLatest hbDataLatest = new HBDataLatest();
					hbDataLatest.setNodeMn(hbDataMode.getNodeMn());
					hbDataLatest.setDataTime(hbDataMode.getDataTime());
					hbDataLatest.setDataIndex(31);
					hbDataLatest.setNodeData(hbDataMode.getNodeData());
					hbDataLatest.setUfrom(recordId);
					hbDataLatest.setItime(nowDate);
					hbDataLatest.setUtime(nowDate);
					hbDataLatestMapper.insertSelective(hbDataLatest);

					HBDataLatestKey hbDataLatestKey = new HBDataLatestKey();
					hbDataLatestKey.setNodeMn(hbDataMode.getNodeMn());
					hbDataLatestMapper.deleteOldestOne(hbDataLatestKey);
					hbDataLatestMapper.updateIndexReduce(hbDataLatestKey);
				}
			} catch (Exception e) {
				hbDataRecord.setPrflag(0);
				hbDataRecord.setPrexp(CommUtil.getMessageFromException(e));
				LOG.error(e);
			}
		}
		try {
			hbDataRecordMapper.insert(hbDataRecord);
			LOG.info("Deal message successfully from " + message.getFromHost());
			return true;
		} catch (Exception e) {
			if (message.getTryTimes() >= 5) {
				LOG.error("Deal the message failed 5 times, remove it. Message: " + message.getMessageBodyTailor());
				return true;
			} else {
				LOG.error(e);
				return false;
			}
		}
	}

	/**
	 * 
	 * @Title: format2KeyValue
	 * @Description: TODO
	 * @param data
	 * @return Optional<KeyValue>
	 * @throws:
	 * @since 1.0.0
	 */
	private Optional<KeyValue> format2KeyValue(String data) {
		Optional<KeyValue> optional;

		if (data.indexOf("=") > 0) {
			String[] array = data.split("=");
			KeyValue keyValue;
			if (array.length >= 2) {
				keyValue = new KeyValue(array[0], array[1]);
			} else {
				keyValue = new KeyValue(array[0], "");
			}
			optional = Optional.of(keyValue);
		} else {
			optional = Optional.ofNullable(null);
		}

		return optional;
	}
}
