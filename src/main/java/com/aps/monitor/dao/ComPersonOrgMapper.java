package com.aps.monitor.dao;

import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.model.ComPersonOrgKey;

public interface ComPersonOrgMapper {
    int deleteByPrimaryKey(ComPersonOrgKey key);

    int insert(ComPersonOrg record);

    int insertSelective(ComPersonOrg record);

    ComPersonOrg selectByPrimaryKey(ComPersonOrgKey key);

    int updateByPrimaryKeySelective(ComPersonOrg record);

    int updateByPrimaryKey(ComPersonOrg record);
}