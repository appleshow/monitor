package com.aps.monitor.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.aps.monitor.dao.HbDataRecordMapper;
import com.aps.monitor.data.Message;
import com.aps.monitor.model.HbDataRecord;
import com.aps.monitor.service.IDealMessage;

/**
 * 
 * @ClassName: DealMessage212
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月2日 下午9:45:21
 * 
 * @since 1.0.0
 */
@Component
public class DealMessage212 implements IDealMessage {
	@Resource
	private HbDataRecordMapper hbDataRecordMapper;
	private static final Logger LOG = LogManager.getLogger(DealMessage212.class);

	@Override
	public boolean save(Message message) {
		try {
			Date nowDate = new Date();
			HbDataRecord hbDataRecord = new HbDataRecord();
			hbDataRecord.setRecordData(message.getMessageTailor());
			hbDataRecord.setPrflag(0);
			hbDataRecord.setUfrom(message.getFromHost());
			hbDataRecord.setItime(nowDate);
			hbDataRecord.setUtime(nowDate);

			hbDataRecordMapper.insertSelective(hbDataRecord);
			return true;
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}

	}

}
