package com.aps.monitor.dao;

import com.aps.monitor.model.HbData;
import com.aps.monitor.model.HbDataKey;

public interface HbDataMapper {
    int deleteByPrimaryKey(HbDataKey key);

    int insert(HbData record);

    int insertSelective(HbData record);

    HbData selectByPrimaryKey(HbDataKey key);

    int updateByPrimaryKeySelective(HbData record);

    int updateByPrimaryKey(HbData record);
}