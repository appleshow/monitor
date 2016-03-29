package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComFormRightsMapper;
import com.aps.monitor.dao.ComOrgFormMapper;
import com.aps.monitor.dao.ComOrgFormRightsMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.service.IFormConfigService;

@Service
public class FormConfigServiceImpl implements IFormConfigService {
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComFormRightsMapper comFormRightMapper;
	@Resource
	private ComCodeMapper comCodeMapper;
	@Resource
	private ComOrgFormMapper comOrgFormMapper;
	@Resource
	private ComOrgFormRightsMapper comOrgFormRightsMapper;

	@Override
	public List<ComForm> selectFormByCondition(ComForm record) {
		return comFormMapper.selectByCondition(record);
	}

	@Override
	public List<ComFormRights> selectFormRightByCondition(ComFormRights record) {
		return comFormRightMapper.selectByCondition(record);
	}

	@Override
	public int insertFormSelective(ComForm record) {
		return comFormMapper.insertSelective(record);
	}

	@Override
	public int insertFormRightSelective(ComFormRights record) {
		int returnValue = 0;
		ComOrgForm comOrgForm;
		List<ComOrgForm> comOrgForms;

		if (null == record.getRea()) {
			record.setRea(0);
		}
		if (null == record.getR1()) {
			record.setR1(0);
		}
		if (null == record.getR2()) {
			record.setR2(0);
		}
		if (null == record.getR3()) {
			record.setR3(0);
		}

		returnValue = comFormRightMapper.insertSelective(record);
		if (returnValue > 0) {
			comOrgForm = new ComOrgForm();
			comOrgForm.setFormId(record.getFormId());
			comOrgForms = comOrgFormMapper.selectByCondition(comOrgForm);
			if (null != comOrgForms) {
				for (ComOrgForm orgForm : comOrgForms) {
					ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

					comOrgFormRights.setOrgId(orgForm.getOrgId());
					comOrgFormRights.setFormId(orgForm.getFormId());
					comOrgFormRights.setRightId(record.getRightId());
					comOrgFormRights.setRea(record.getRea());
					comOrgFormRights.setRel(0);
					comOrgFormRights.setR1(record.getR1());
					comOrgFormRights.setR2(record.getR2());
					comOrgFormRights.setR3(record.getR3());
					comOrgFormRights.setIperson(record.getIperson());
					comOrgFormRights.setItime(record.getItime());
					comOrgFormRights.setUperson(record.getUperson());
					comOrgFormRights.setUtime(record.getUtime());

					comOrgFormRightsMapper.insertSelective(comOrgFormRights);
				}
			}
		}

		return returnValue;
	}

	@Override
	public int updateFormBySelective(ComForm record) {
		return comFormMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateFormRightBySelective(ComFormRights record) {
		int returnValue = 0;

		returnValue = comFormRightMapper.updateByPrimaryKeySelective(record);
		if (returnValue > 0) {
			ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

			comOrgFormRights.setFormId(record.getFormId());
			comOrgFormRights.setRightId(record.getRightId());
			comOrgFormRights.setRea(record.getRea());
			comOrgFormRights.setR1(record.getR1());
			comOrgFormRights.setR2(record.getR2());
			comOrgFormRights.setR3(record.getR3());
			comOrgFormRights.setUperson(record.getUperson());
			comOrgFormRights.setUtime(record.getUtime());
			
			comOrgFormRightsMapper.updateByPrimaryKeySelective(comOrgFormRights);
		}
		
		return returnValue;
	}

	@Override
	public int deleteFormByPrimaryKey(Integer formId) {
		int returnValue = 0;
		ComOrgFormRights comOrgFormRights = new ComOrgFormRights();
		ComOrgForm comOrgForm = new ComOrgForm();
		ComFormRights comFormRights = new ComFormRights();

		comOrgFormRights.setFormId(formId);
		comOrgFormRightsMapper.deleteByPrimaryKey(comOrgFormRights);
		comFormRights.setFormId(formId);
		comFormRightMapper.deleteByPrimaryKey(comFormRights);
		comOrgForm.setFormId(formId);
		comOrgFormMapper.deleteByPrimaryKey(comOrgForm);

		returnValue = comFormMapper.deleteByPrimaryKey(formId);

		return returnValue;
	}

	@Override
	public int deleteFormRightsByPrimaryKey(ComFormRights record) {
		int returnValue = 0;
		ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

		comOrgFormRights.setFormId(record.getFormId());
		comOrgFormRights.setRightId(record.getRightId());
		comOrgFormRightsMapper.deleteByPrimaryKey(comOrgFormRights);

		returnValue = comFormRightMapper.deleteByPrimaryKey(record);
		return returnValue;
	}

	@Override
	public List<ComCode> selectFormCtlType(List<String> codeType) {
		return comCodeMapper.selectCombData(codeType);
	}

}
