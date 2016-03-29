package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.dao.ComPersonOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.model.ComPersonOrgKey;
import com.aps.monitor.service.IPersonOrgConfigService;

@Service
public class PersonOrgConfigServiceImpl implements IPersonOrgConfigService {
	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComPersonOrgMapper comPeronOrgMapper;

	@Override
	public List<ComPerson> selectComPersonByCondition(ComPerson record) {
		return comPersonMapper.selectByCondition(record);
	}

	@Override
	public List<ComOrg> selectComOrgByCondition(ComOrg record) {
		return comOrgMapper.selectByCondition(record);
	}

	@Override
	public List<ComPersonOrg> selectByCondition(ComPerson record) {
		return comPeronOrgMapper.selectByCondition(record);
	}

	@Override
	public int insertSelective(ComPersonOrg record) {
		record.setPrtype("1");
		return comPeronOrgMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComPersonOrg record) {
		return comPeronOrgMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(ComPersonOrgKey key) {
		return comPeronOrgMapper.deleteByPrimaryKey(key);
	}

}
