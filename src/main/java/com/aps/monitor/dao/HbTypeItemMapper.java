package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.model.HbTypeItemKey;

public interface HbTypeItemMapper {
	int deleteByPrimaryKey(HbTypeItemKey key);

	int insert(HbTypeItem record);

	int insertSelective(HbTypeItem record);

	HbTypeItem selectByPrimaryKey(HbTypeItemKey key);

	List<HbTypeItem> selectByCondition(HbTypeItem record);

	int updateByPrimaryKeySelective(HbTypeItem record);

	int updateByPrimaryKey(HbTypeItem record);
}