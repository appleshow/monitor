package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComPerson;

public interface ILoginViewService {

	ComPerson selectByPrimaryKey(Integer personId);

	ComPerson selectByUserName(String userName);

	List<ComOrgFormRights> selectPersonPermissions(Integer personId);
}
