package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.CommUtil;
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
	 * <p>
	 * Title: referOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		try {
			comOrgs = comOrgMapper.selectByCondition(comOrg);
			responseData.setData(comOrgs);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referForm
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referForm(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referForm(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		try {
			comForms = comFormMapper.selectByCondition(comForm);
			responseData.setData(comForms);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referOrgForm
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referOrgForm(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referOrgForm(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComOrgForm comOrgForm = new ComOrgForm();
		List<ComOrgForm> comOrgForms;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comOrgForm.setOrgId(requestRefPar.getIntegerPar("orgId"));
			comOrgForm.setFormId(requestRefPar.getIntegerPar("formId"));
			comOrgForms = comOrgFormMapper.selectByCondition(comOrgForm);
			responseData.setData(comOrgForms);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referFormRights
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referFormRights(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referFormRights(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComFormRights comFormRights = new ComFormRights();
		List<ComFormRights> comFormRightss;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
			comFormRightss = comFormRightsMapper.selectByCondition(comFormRights);
			responseData.setData(comFormRightss);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referOrgFormRights
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referOrgFormRights(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComOrgFormRights comOrgFormRights = new ComOrgFormRights();
		List<ComOrgFormRights> comOrgFormRightss;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comOrgFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
			comOrgFormRights.setOrgId(requestRefPar.getIntegerPar("orgId"));
			comOrgFormRightss = comOrgFormRightsMapper.selectByCondition(comOrgFormRights);
			responseData.setData(comOrgFormRightss);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referComCode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#referComCode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referCombCode(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComCode comCode = new ComCode();
		List<ComCode> comCodes;

		try {
			comCode.setCodeType("C0001");
			comCodes = comCodemapper.selectByCondition(comCode);
			responseData.setData(comCodes);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: modifyComOrgForm
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#modifyComOrgForm(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyOrgForm(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgForm comOrgForm;
		ComOrgFormRights comOrgFormRights;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
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
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: modifyComOrgFormRights
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgFormConfigService#modifyComOrgFormRights(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgFormRights comOrgFormRights;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
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
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}
}
