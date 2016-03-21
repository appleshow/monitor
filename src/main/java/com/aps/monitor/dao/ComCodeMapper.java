package com.aps.monitor.dao;

import java.util.List;
import java.util.Map;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComCodeKey;

public interface ComCodeMapper {
	int deleteByPrimaryKey(ComCodeKey key);

	int insert(ComCode record);

	int insertSelective(ComCode record);

	ComCode selectByPrimaryKey(ComCodeKey key);

	List<ComCode> selectByCondition(ComCode record);

	List<ComCode> selectCombData(List<String> codeType);

	int updateByPrimaryKeySelective(ComCode record);

	int updateByPrimaryKeyMap(Map<String, String> recode);

	int updateByPrimaryKey(ComCode record);

}