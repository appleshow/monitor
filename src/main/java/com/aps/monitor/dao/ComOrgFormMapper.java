package com.aps.monitor.dao;

import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;

public interface ComOrgFormMapper {
    int deleteByPrimaryKey(ComOrgFormKey key);

    int insert(ComOrgForm record);

    int insertSelective(ComOrgForm record);

    ComOrgForm selectByPrimaryKey(ComOrgFormKey key);

    int updateByPrimaryKeySelective(ComOrgForm record);

    int updateByPrimaryKey(ComOrgForm record);
}