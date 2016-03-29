package com.aps.monitor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.dao.ComPersonOrgMapper;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.service.IPersonConfigService;

@Service
public class PersonConfigServiceImpl implements IPersonConfigService {
	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComPersonOrgMapper comPersonOrgMapper;

	@Override
	public int deleteByPrimaryKey(Integer personId) {
		int returnValue = 0;
		ComPersonOrg comPersonOrg = new ComPersonOrg();

		comPersonOrg.setPersonId(personId);
		comPersonOrgMapper.deleteByPrimaryKey(comPersonOrg);
		returnValue = comPersonMapper.deleteByPrimaryKey(personId);

		return returnValue;
	}

	@Override
	public int insert(ComPerson record) {
		return comPersonMapper.insert(record);
	}

	@Override
	public int insertSelective(ComPerson record) {
		int returnValue = 0;

		returnValue = comPersonMapper.insertSelective(record);
		if (returnValue > 0) {
			ComPersonOrg comPersonOrg = new ComPersonOrg();

			comPersonOrg.setPersonId(record.getPersonId());
			comPersonOrg.setOrgId(record.getUserOrg());
			comPersonOrg.setPrflag(1);
			comPersonOrg.setPrtype("0");
			comPersonOrg.setIperson(record.getIperson());
			comPersonOrg.setItime(record.getItime());
			comPersonOrg.setIperson(record.getIperson());
			comPersonOrg.setUtime(record.getUtime());

			comPersonOrgMapper.insertSelective(comPersonOrg);
		}

		return returnValue;
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
