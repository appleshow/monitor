package com.aps.monitor.service;

import java.util.List;
import java.util.Map;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComCodeKey;

public interface ICodeConfigService {
	int deleteByPrimaryKey(ComCodeKey key);

	int insert(ComCode record);

	int insertSelective(ComCode record);

	ComCode selectByPrimaryKey(ComCodeKey key);

	int updateByPrimaryKeySelective(ComCode record);

	int updateByPrimaryKeyMap(Map<String, String> recode);

	int updateByPrimaryKey(ComCode record);

	List<ComCode> selectByCondition(ComCode record);

}
