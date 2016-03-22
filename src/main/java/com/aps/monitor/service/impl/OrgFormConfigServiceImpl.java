package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComFormRightsMapper;
import com.aps.monitor.dao.ComOrgFormRightsMapper;
import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComOrgFormRightsKey;
import com.aps.monitor.service.IOrgFormConfigService;

@Service
public class OrgFormConfigServiceImpl implements IOrgFormConfigService {
	@Resource
	private ComCodeMapper comCodeMapper;
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComFormRightsMapper comFormRightMapper;
	@Resource
	private ComOrgFormRightsMapper comOrgFormMapper;

	@Override
	public List<ComCode> selectCombData(List<String> codeType) {
		return comCodeMapper.selectCombData(codeType);
	}

	@Override
	public List<ComOrg> selectByCondition(ComOrg record) {
		return comOrgMapper.selectByCondition(record);
	}

	@Override
	public List<ComForm> selectByCondition(ComForm record) {
		return comFormMapper.selectByCondition(record);
	}

	@Override
	public int deleteByPrimaryKey(ComOrgFormRightsKey key) {
		return comOrgFormMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insertSelective(ComOrgFormRights record) {
		return comOrgFormMapper.insertSelective(record);
	}

	@Override
	public List<ComOrgFormRights> selectByCondition(ComOrgFormRights record) {
		return comOrgFormMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComOrgFormRights record) {
		return comOrgFormMapper.updateByPrimaryKeySelective(record);
	}
}
