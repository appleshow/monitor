package com.aps.monitor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.ILoginViewService;

@Service
public class LoginViewServiceImpl implements ILoginViewService {

	@Resource
	private ComPersonMapper comPersonMapper;

	@Override
	public ComPerson selectByPrimaryKey(Integer personId) {
		return this.comPersonMapper.selectByPrimaryKey(personId);
	}

	@Override
	public ComPerson selectByUserName(String userName) {
		return this.comPersonMapper.selectByUserName(userName);
	}

}
