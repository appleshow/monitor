package com.aps.monitor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.service.IOrgConfigService;

@Service
public class OrgConfigServiceImpl implements IOrgConfigService {
	@Resource
	private ComOrgMapper comOrgMapper;

	@Override
	public int deleteByPrimaryKey(Integer orgId) {
		return comOrgMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public int insert(ComOrg record) {
		return comOrgMapper.insert(record);
	}

	@Override
	public int insertSelective(ComOrg record) {
		return comOrgMapper.insertSelective(record);
	}

	@Override
	public ComOrg selectByPrimaryKey(Integer orgId) {
		return comOrgMapper.selectByPrimaryKey(orgId);
	}

	@Override
	public List<ComOrg> selectByCondition(ComOrg record) {
		return comOrgMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComOrg record) {
		return comOrgMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeyMap(Map<String, String> recode) {
		return comOrgMapper.updateByPrimaryKeyMap(recode);
	}

	@Override
	public int updateByPrimaryKey(ComOrg record) {
		return comOrgMapper.updateByPrimaryKey(record);
	}

}
