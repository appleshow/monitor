package com.aps.monitor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.IPersonConfigService;

@Service
public class PersonConfigServiceImpl implements IPersonConfigService {
	@Resource
	private ComPersonMapper comPersonMapper;

	@Override
	public int deleteByPrimaryKey(Integer personId) {
		return comPersonMapper.deleteByPrimaryKey(personId);
	}

	@Override
	public int insert(ComPerson record) {
		return comPersonMapper.insert(record);
	}

	@Override
	public int insertSelective(ComPerson record) {
		return comPersonMapper.insertSelective(record);
	}

	@Override
	public ComPerson selectByPrimaryKey(Integer personId) {
		return comPersonMapper.selectByPrimaryKey(personId);
	}

	@Override
	public ComPerson selectByUserName(String userId) {
		return comPersonMapper.selectByUserName(userId);
	}

	@Override
	public List<ComPerson> selectByCondition(ComPerson record) {
		return comPersonMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComPerson record) {
		return comPersonMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyMap(Map<String, String> recode) {
		return comPersonMapper.updateByPrimaryKeyMap(recode);
	}

	@Override
	public int updateByPrimaryKey(ComPerson record) {
		return comPersonMapper.updateByPrimaryKey(record);
	}

}
