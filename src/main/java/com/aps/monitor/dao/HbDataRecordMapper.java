package com.aps.monitor.dao;

import com.aps.monitor.model.HbDataRecord;

public interface HbDataRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(HbDataRecord record);

    int insertSelective(HbDataRecord record);

    HbDataRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(HbDataRecord record);

    int updateByPrimaryKey(HbDataRecord record);
}