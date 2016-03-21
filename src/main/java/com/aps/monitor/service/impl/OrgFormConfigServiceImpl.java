package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComFormRightMapper;
import com.aps.monitor.dao.ComOrgFormMapper;
import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;
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
	private ComFormRightMapper comFormRightMapper;
	@Resource
	private ComOrgFormMapper comOrgFormMapper;

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
	public int deleteByPrimaryKey(ComOrgFormKey key) {
		return comOrgFormMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insertSelective(ComOrgForm record) {
		return comOrgFormMapper.insertSelective(record);
	}

	@Override
	public List<ComOrgForm> selectByCondition(ComOrgForm record) {
		return comOrgFormMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComOrgForm record) {
		return comOrgFormMapper.updateByPrimaryKeySelective(record);
	}
}
