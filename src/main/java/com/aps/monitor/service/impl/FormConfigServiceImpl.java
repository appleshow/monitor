package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComFormRightMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRight;
import com.aps.monitor.model.ComFormRightKey;
import com.aps.monitor.service.IFormConfigService;

@Service
public class FormConfigServiceImpl implements IFormConfigService {
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComFormRightMapper comFormRightMapper;
	@Resource
	private ComCodeMapper comCodeMapper;

	@Override
	public List<ComForm> selectFormByCondition(ComForm record) {
		return comFormMapper.selectByCondition(record);
	}

	@Override
	public List<ComFormRight> selectFormRightByCondition(ComFormRight record) {
		return comFormRightMapper.selectByCondition(record);
	}

	@Override
	public int insertFormSelective(ComForm record) {
		return comFormMapper.insertSelective(record);
	}

	@Override
	public int insertFormRightSelective(ComFormRight record) {
		return comFormRightMapper.insertSelective(record);
	}

	@Override
	public int updateFormBySelective(ComForm record) {
		return comFormMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateFormRightBySelective(ComFormRight record) {
		return comFormRightMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteFormByPrimaryKey(Integer formId) {
		return comFormMapper.deleteByPrimaryKey(formId);
	}

	@Override
	public int deleteFormRightByPrimaryKey(ComFormRightKey key) {
		return comFormRightMapper.deleteByPrimaryKey(key);
	}

	@Override
	public List<ComCode> selectFormCtlType(List<String> codeType) {
		return comCodeMapper.selectCombData(codeType);
	}
}
