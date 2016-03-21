package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComFormRight;
import com.aps.monitor.model.ComFormRightKey;

public interface ComFormRightMapper {
	int deleteByPrimaryKey(ComFormRightKey key);

	int insert(ComFormRight record);

	int insertSelective(ComFormRight record);

	ComFormRight selectByPrimaryKey(ComFormRightKey key);

	List<ComFormRight> selectByCondition(ComFormRight record);

	int updateByPrimaryKeySelective(ComFormRight record);

	int updateByPrimaryKey(ComFormRight record);
}