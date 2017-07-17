package com.aps.monitor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HBDataLatestKey {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataTime;

	private String nodeMn;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getNodeMn() {
		return nodeMn;
	}

	public void setNodeMn(String nodeMn) {
		this.nodeMn = nodeMn == null ? null : nodeMn.trim();
	}
}