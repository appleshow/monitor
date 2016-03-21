package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComForm;

public interface ComFormMapper {
	int deleteByPrimaryKey(Integer formId);

	int insert(ComForm record);

	int insertSelective(ComForm record);

	ComForm selectByPrimaryKey(Integer formId);

	List<ComForm> selectByCondition(ComForm record);

	List<ComForm> selectCombData(ComForm record);

	int updateByPrimaryKeySelective(ComForm record);

	int updateByPrimaryKey(ComForm record);
}