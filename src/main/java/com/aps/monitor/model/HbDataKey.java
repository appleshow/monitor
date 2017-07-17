package com.aps.monitor.model;

import java.util.Date;

import com.aps.monitor.comm.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class HbDataKey {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataTime;

	private String dataType;

	private String nodeMn;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	/**
	 * 
	 * @Title: setDataTime
	 * @Description: TODO
	 * @param DataTimeStr
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	public void setDataTime(String DataTimeStr) {
		this.dataTime = DateUtil.fromString(DataTimeStr, DateUtil.SIMPLE_DATE_FORMAT1);
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType == null ? null : dataType.trim();
	}

	public String getNodeMn() {
		return nodeMn;
	}

	public void setNodeMn(String nodeMn) {
		this.nodeMn = nodeMn == null ? null : nodeMn.trim();
	}
}