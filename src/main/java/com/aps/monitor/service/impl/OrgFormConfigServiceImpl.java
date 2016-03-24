package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComFormRightsMapper;
import com.aps.monitor.dao.ComOrgFormMapper;
import com.aps.monitor.dao.ComOrgFormRightsMapper;
import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComOrgFormRightsKey;
import com.aps.monitor.service.IOrgFormConfigService;

@Service
public class OrgFormConfigServiceImpl implements IOrgFormConfigService {
	@Resource
	private ComCodeMapper comCodemapper;
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComOrgFormMapper comOrgFormMapper;
	@Resource
	private ComFormRightsMapper comFormRightsMapper;
	@Resource
	private ComOrgFormRightsMapper comOrgFormRightsMapper;

	@Override
	public List<ComCode> selectComCode(ComCode record) {
		return comCodemapper.selectByCondition(record);
	}

	@Override
	public List<ComOrg> selectComOrg(ComOrg record) {
		return comOrgMapper.selectByCondition(record);
	}

	@Override
	public List<ComForm> selectComForm(ComForm record) {
		return comFormMapper.selectByCondition(record);
	}

	@Override
	public List<ComOrgForm> selectComOrgForm(ComOrgForm record) {
		return comOrgFormMapper.selectByCondition(record);
	}

	@Override
	public List<ComFormRights> selectComFormRights(ComFormRights record) {
		return comFormRightsMapper.selectByCondition(record);
	}

	@Override
	public int deleteByPrimaryKey(ComOrgFormRightsKey key) {
		return comOrgFormRightsMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insertSelective(ComOrgFormRights record) {
		return comOrgFormRightsMapper.insertSelective(record);
	}

	@Override
	public List<ComOrgFormRights> selectByCondition(ComOrgFormRights record) {
		return comOrgFormRightsMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComOrgFormRights record) {
		return comOrgFormRightsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertComOrgFormSelective(ComOrgForm record) {
		return comOrgFormMapper.insertSelective(record);
	}

	@Override
	public int deleteComOrgFormByPrimaryKey(ComOrgFormKey record) {
		return comOrgFormMapper.deleteByPrimaryKey(record);
	}
}
