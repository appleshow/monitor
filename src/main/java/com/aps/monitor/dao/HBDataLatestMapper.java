package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.HBDataLatest;
import com.aps.monitor.model.HBDataLatestKey;

public interface HBDataLatestMapper {
	int deleteByPrimaryKey(HBDataLatestKey key);

	int insert(HBDataLatest record);

	int insertSelective(HBDataLatest record);

	HBDataLatest selectByPrimaryKey(HBDataLatestKey key);

	List<HBDataLatest> selectByCondition(HBDataLatest record);

	List<HBDataLatest> selectAllLastOne(HBDataLatest record);

	List<HBDataLatest> selectByPerson(HBDataLatest record);

	int updateByPrimaryKeySelective(HBDataLatest record);

	int updateByPrimaryKey(HBDataLatest record);

	int deleteOldestOne(HBDataLatestKey key);

	int updateIndexReduce(HBDataLatestKey key);
}