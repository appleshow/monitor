package com.aps.monitor.dao;

import com.aps.monitor.model.HbDataRecord;

public interface HbDataRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(HbDataRecord record);

    int insertSelective(HbDataRecord record);

    HbDataRecord selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(HbDataRecord record);

    int updateByPrimaryKey(HbDataRecord record);
}