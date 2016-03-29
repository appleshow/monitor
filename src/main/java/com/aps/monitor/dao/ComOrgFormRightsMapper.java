package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComOrgFormRightsKey;

public interface ComOrgFormRightsMapper {
	int deleteByPrimaryKey(ComOrgFormRightsKey key);
	
	int insert(ComOrgFormRights record);

	int insertSelective(ComOrgFormRights record);

	ComOrgFormRights selectByPrimaryKey(ComOrgFormRightsKey key);

	List<ComOrgFormRights> selectByCondition(ComOrgFormRights record);

	int updateByPrimaryKeySelective(ComOrgFormRights record);

	int updateByPrimaryKey(ComOrgFormRights record);
}