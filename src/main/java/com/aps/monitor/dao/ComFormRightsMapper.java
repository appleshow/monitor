package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComFormRightsKey;

public interface ComFormRightsMapper {
	int deleteByPrimaryKey(ComFormRightsKey key);

	int insert(ComFormRights record);

	int insertSelective(ComFormRights record);

	ComFormRights selectByPrimaryKey(ComFormRightsKey key);

	List<ComFormRights> selectByCondition(ComFormRights record);

	int updateByPrimaryKeySelective(ComFormRights record);

	int updateByPrimaryKey(ComFormRights record);
}