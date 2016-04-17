package com.aps.monitor.dao;

import java.util.List;
import com.aps.monitor.model.HbType;

public interface HbTypeMapper {
	int deleteByPrimaryKey(Integer typeId);

	int insert(HbType record);

	int insertSelective(HbType record);

	HbType selectByPrimaryKey(Integer typeId);

	List<HbType> selectByCondition(HbType record);

	int updateByPrimaryKeySelective(HbType record);

	int updateByPrimaryKey(HbType record);
}