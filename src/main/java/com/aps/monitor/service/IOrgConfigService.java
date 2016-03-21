package com.aps.monitor.service;

import java.util.List;
import java.util.Map;

import com.aps.monitor.model.ComOrg;

public interface IOrgConfigService {
	int deleteByPrimaryKey(Integer orgId);

	int insert(ComOrg record);

	int insertSelective(ComOrg record);

	ComOrg selectByPrimaryKey(Integer orgId);

	List<ComOrg> selectByCondition(ComOrg record);

	int updateByPrimaryKeySelective(ComOrg record);

	int updateByPrimaryKeyMap(Map<String, String> recode);

	int updateByPrimaryKey(ComOrg record);
}
