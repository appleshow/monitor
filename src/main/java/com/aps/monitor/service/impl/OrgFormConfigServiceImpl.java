package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
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
import com.aps.monitor.model.ComOrgFormRights;
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

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = comOrgMapper.selectByCondition(comOrg);
		responseData.setData(comOrgs);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referForm(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = comFormMapper.selectByCondition(comForm);
		responseData.setData(comForms);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referOrgForm(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComOrgForm comOrgForm = new ComOrgForm();
		List<ComOrgForm> comOrgForms;

		comOrgForm.setOrgId(requestRefPar.getIntegerPar("orgId"));
		comOrgForm.setFormId(requestRefPar.getIntegerPar("formId"));
		comOrgForms = comOrgFormMapper.selectByCondition(comOrgForm);
		responseData.setData(comOrgForms);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referFormRights(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComFormRights comFormRights = new ComFormRights();
		List<ComFormRights> comFormRightss;

		comFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
		comFormRightss = comFormRightsMapper.selectByCondition(comFormRights);
		responseData.setData(comFormRightss);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referOrgFormRights(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComOrgFormRights comOrgFormRights = new ComOrgFormRights();
		List<ComOrgFormRights> comOrgFormRightss;

		comOrgFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
		comOrgFormRights.setOrgId(requestRefPar.getIntegerPar("orgId"));
		comOrgFormRightss = comOrgFormRightsMapper.selectByCondition(comOrgFormRights);
		responseData.setData(comOrgFormRightss);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referCombCode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComCode comCode = new ComCode();
		List<ComCode> comCodes;

		comCode.setCodeType("C0001");
		comCodes = comCodemapper.selectByCondition(comCode);
		responseData.setData(comCodes);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyOrgForm(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgForm comOrgForm;
		ComOrgFormRights comOrgFormRights;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comOrgForm = (ComOrgForm) JsonUtil.readValueAsObject(rowData, ComOrgForm.class);
			if (null != comOrgForm) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					comOrgForm.setItime(now);
					comOrgForm.setIperson(personId);
					comOrgForm.setUtime(now);
					comOrgForm.setUperson(personId);
					comOrgFormMapper.insertSelective(comOrgForm);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					comOrgFormMapper.deleteByPrimaryKey(comOrgForm);
					comOrgFormRights = new ComOrgFormRights();

					comOrgFormRights.setOrgId(comOrgForm.getOrgId());
					comOrgFormRights.setFormId(comOrgForm.getFormId());

					comOrgFormRightsMapper.deleteByPrimaryKey(comOrgFormRights);
					break;
				default:
					break;
				}
			} else {
				jsonParseException = true;
				break;
			}
		}

		if (jsonParseException) {
			responseData.setCode(-108);
			responseData.setMessage("数据处理异常，请检查输入数据！");
		} else {
			responseData.setCode(0);
		}
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyOrgFormRights(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgFormRights comOrgFormRights;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comOrgFormRights = (ComOrgFormRights) JsonUtil.readValueAsObject(rowData, ComOrgFormRights.class);
			if (null != comOrgFormRights) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					comOrgFormRights.setItime(now);
					comOrgFormRights.setIperson(personId);
					comOrgFormRights.setUtime(now);
					comOrgFormRights.setUperson(personId);
					if (null == comOrgFormRights.getRea()) {
						comOrgFormRights.setRea(0);
					}
					if (null == comOrgFormRights.getR1()) {
						comOrgFormRights.setR1(0);
					}
					if (null == comOrgFormRights.getR2()) {
						comOrgFormRights.setR2(0);
					}
					if (null == comOrgFormRights.getR3()) {
						comOrgFormRights.setR3(0);
					}
					comOrgFormRightsMapper.insertSelective(comOrgFormRights);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					comOrgFormRights.setUtime(now);
					comOrgFormRights.setUperson(personId);
					comOrgFormRightsMapper.updateByPrimaryKeySelective(comOrgFormRights);
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					break;
				default:
					break;
				}
			} else {
				jsonParseException = true;
				break;
			}
		}

		if (jsonParseException) {
			responseData.setCode(-108);
			responseData.setMessage("数据处理异常，请检查输入数据！");
		} else {
			responseData.setCode(0);
		}
	}
}
