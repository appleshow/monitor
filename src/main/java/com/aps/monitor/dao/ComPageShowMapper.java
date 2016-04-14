package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComPageShow;
import com.aps.monitor.model.ComPageShowKey;

public interface ComPageShowMapper {
	int deleteByPrimaryKey(ComPageShowKey key);

	int insert(ComPageShow record);

	int insertSelective(ComPageShow record);

	ComPageShow selectByPrimaryKey(ComPageShowKey key);

	List<ComPageShow> selectByCondition(ComPageShow record);

	int updateByPrimaryKeySelective(ComPageShow record);

	int updateByPrimaryKey(ComPageShow record);
}