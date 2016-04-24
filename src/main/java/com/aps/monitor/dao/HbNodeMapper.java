package com.aps.monitor.dao;

import com.aps.monitor.model.HbNode;

public interface HbNodeMapper {
    int deleteByPrimaryKey(Integer nodeId);

    int insert(HbNode record);

    int insertSelective(HbNode record);

    HbNode selectByPrimaryKey(Integer nodeId);

    int updateByPrimaryKeySelective(HbNode record);

    int updateByPrimaryKey(HbNode record);
}