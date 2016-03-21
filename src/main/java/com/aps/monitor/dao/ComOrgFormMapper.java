package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;

public interface ComOrgFormMapper {
	int deleteByPrimaryKey(ComOrgFormKey key);

	int insert(ComOrgForm record);

	int insertSelective(ComOrgForm record);

	ComOrgForm selectByPrimaryKey(ComOrgFormKey key);

	List<ComOrgForm> selectByCondition(ComOrgForm record);

	int updateByPrimaryKeySelective(ComOrgForm record);

	int updateByPrimaryKey(ComOrgForm record);
}