package com.aps.monitor.dao;

import com.aps.monitor.model.HbDataTable;

public interface HbDataTableMapper {
	int delete(HbDataTable record);

	int create(HbDataTable record);
}