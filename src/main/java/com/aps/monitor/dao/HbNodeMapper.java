package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.HbNode;

public interface HbNodeMapper {
	int deleteByPrimaryKey(Integer nodeId);

	int insert(HbNode record);

	int insertSelective(HbNode record);

	HbNode selectByPrimaryKey(Integer nodeId);

	List<HbNode> selectByCondition(HbNode record);

	int updateByPrimaryKeySelective(HbNode record);

	int updateByPrimaryKey(HbNode record);
}