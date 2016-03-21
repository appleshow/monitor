package com.aps.monitor.dao;

import com.aps.monitor.model.ComPersonMsg;
import com.aps.monitor.model.ComPersonMsgKey;

public interface ComPersonMsgMapper {
    int deleteByPrimaryKey(ComPersonMsgKey key);

    int insert(ComPersonMsg record);

    int insertSelective(ComPersonMsg record);

    ComPersonMsg selectByPrimaryKey(ComPersonMsgKey key);

    int updateByPrimaryKeySelective(ComPersonMsg record);

    int updateByPrimaryKey(ComPersonMsg record);
}