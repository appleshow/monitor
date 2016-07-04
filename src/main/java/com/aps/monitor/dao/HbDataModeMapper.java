package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.HbDataMode;
import com.aps.monitor.model.HbDataTable;

public interface HbDataModeMapper {
	int deleteByCondition(HbDataTable key);

	int insert(HbDataMode record);

	int insertSelective(HbDataMode record);

	List<HbDataMode> selectByCondition(HbDataTable key);

}