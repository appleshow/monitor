package com.aps.monitor.service;

import java.util.List;
import java.util.Map;

import com.aps.monitor.model.ComPerson;

public interface IPersonConfigService {

	int deleteByPrimaryKey(Integer personId);

	int insert(ComPerson record);

	int insertSelective(ComPerson record);

	ComPerson selectByPrimaryKey(Integer personId);

	ComPerson selectByUserName(String userId);

	List<ComPerson> selectByCondition(ComPerson record);

	int updateByPrimaryKeySelective(ComPerson record);

	int updateByPrimaryKeyMap(Map<String, String> recode);

	int updateByPrimaryKey(ComPerson record);

}
