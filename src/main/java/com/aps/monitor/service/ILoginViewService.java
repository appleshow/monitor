package com.aps.monitor.service;

import com.aps.monitor.model.ComPerson;

public interface ILoginViewService {

	ComPerson selectByPrimaryKey(Integer personId);

	ComPerson selectByUserName(String userName);
}
