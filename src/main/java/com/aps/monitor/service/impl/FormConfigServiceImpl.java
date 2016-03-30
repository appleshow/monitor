package com.aps.monitor.service.impl;

import java.util.ArrayList;
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
import com.aps.monitor.comm.SystemProperty;
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
	 * @see com.aps.monitor.service.IFormConfigService#referForm(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referForm(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<?> comForms;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comForm.setPrgroup(requestRefPar.getStringPar("prgroup"));
			comForm.setFormName(requestRefPar.getStringPar("formName"));
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
	 * Title: modifyForm
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IFormConfigService#modifyForm(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyForm(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComForm comForm;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comForm = (ComForm) JsonUtil.readValueAsObject(rowData, ComForm.class);
				if (null != comForm) {
					personId = requestMdyPar.getPersonId(httpSession, now, rowData);
					switch (type) {
						case SystemProperty.MODIFY_TYPE_INSERT:
							comForm.setItime(now);
							comForm.setIperson(personId);
							comForm.setUtime(now);
							comForm.setUperson(personId);
							comFormMapper.insertSelective(comForm);
							break;
						case SystemProperty.MODIFY_TYPE_UPDATE:
							comFormMapper.updateByPrimaryKeySelective(comForm);
							break;
						case SystemProperty.MODIFY_TYPE_DELETE:
							ComOrgFormRights comOrgFormRights = new ComOrgFormRights();
							ComOrgForm comOrgForm = new ComOrgForm();
							ComFormRights comFormRights = new ComFormRights();

							comOrgFormRights.setFormId(comForm.getFormId());
							comOrgFormRightsMapper.deleteByPrimaryKey(comOrgFormRights);
							comFormRights.setFormId(comForm.getFormId());
							comFormRightMapper.deleteByPrimaryKey(comFormRights);
							comOrgForm.setFormId(comForm.getFormId());
							comOrgFormMapper.deleteByPrimaryKey(comOrgForm);

							comFormMapper.deleteByPrimaryKey(comForm.getFormId());
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
	 * Title: referFormRight
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IFormConfigService#referFormRight(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referFormRight(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComFormRights comFormRight = new ComFormRights();
		List<?> comFormRights;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comFormRight.setFormId(requestRefPar.getIntegerPar("formId"));
			comFormRights = comFormRightMapper.selectByCondition(comFormRight);

			responseData.setData(comFormRights);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}

	}

	/**
	 * 
	 * <p>
	 * Title: modifyFormRight
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IFormConfigService#modifyFormRight(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyFormRight(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId, returnValue;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComFormRights comFormRight;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comFormRight = (ComFormRights) JsonUtil.readValueAsObject(rowData, ComFormRights.class);
				if (null != comFormRight) {
					personId = requestMdyPar.getPersonId(httpSession, now, rowData);
					switch (type) {
						case SystemProperty.MODIFY_TYPE_INSERT:
							if (null == comFormRight.getRea()) {
								comFormRight.setRea(0);
							}
							if (null == comFormRight.getR1()) {
								comFormRight.setR1(0);
							}
							if (null == comFormRight.getR2()) {
								comFormRight.setR2(0);
							}
							if (null == comFormRight.getR3()) {
								comFormRight.setR3(0);
							}
							comFormRight.setItime(now);
							comFormRight.setIperson(personId);
							comFormRight.setUtime(now);
							comFormRight.setUperson(personId);
							returnValue = comFormRightMapper.insertSelective(comFormRight);

							if (returnValue > 0) {
								ComOrgForm comOrgForm;
								List<ComOrgForm> comOrgForms;

								comOrgForm = new ComOrgForm();
								comOrgForm.setFormId(comFormRight.getFormId());
								comOrgForms = comOrgFormMapper.selectByCondition(comOrgForm);
								if (null != comOrgForms) {
									for (ComOrgForm orgForm : comOrgForms) {
										ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

										comOrgFormRights.setOrgId(orgForm.getOrgId());
										comOrgFormRights.setFormId(orgForm.getFormId());
										comOrgFormRights.setRightId(comFormRight.getRightId());
										comOrgFormRights.setRea(comFormRight.getRea());
										comOrgFormRights.setRel(0);
										comOrgFormRights.setR1(comFormRight.getR1());
										comOrgFormRights.setR2(comFormRight.getR2());
										comOrgFormRights.setR3(comFormRight.getR3());
										comOrgFormRights.setIperson(comFormRight.getIperson());
										comOrgFormRights.setItime(comFormRight.getItime());
										comOrgFormRights.setUperson(comFormRight.getUperson());
										comOrgFormRights.setUtime(comFormRight.getUtime());

										comOrgFormRightsMapper.insertSelective(comOrgFormRights);
									}
								}
							}

							break;
						case SystemProperty.MODIFY_TYPE_UPDATE:
							returnValue = comFormRightMapper.updateByPrimaryKeySelective(comFormRight);
							if (returnValue > 0) {
								ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

								comOrgFormRights.setFormId(comFormRight.getFormId());
								comOrgFormRights.setRightId(comFormRight.getRightId());
								comOrgFormRights.setRea(comFormRight.getRea());
								comOrgFormRights.setR1(comFormRight.getR1());
								comOrgFormRights.setR2(comFormRight.getR2());
								comOrgFormRights.setR3(comFormRight.getR3());
								comOrgFormRights.setUperson(comFormRight.getUperson());
								comOrgFormRights.setUtime(comFormRight.getUtime());

								comOrgFormRightsMapper.updateByPrimaryKeySelective(comOrgFormRights);
							}

							break;
						case SystemProperty.MODIFY_TYPE_DELETE:
							ComOrgFormRights comOrgFormRights = new ComOrgFormRights();

							comOrgFormRights.setFormId(comFormRight.getFormId());
							comOrgFormRights.setRightId(comFormRight.getRightId());
							comOrgFormRightsMapper.deleteByPrimaryKey(comOrgFormRights);

							comFormRightMapper.deleteByPrimaryKey(comFormRight);

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
	 * Title: referFormCtlType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IFormConfigService#referFormCtlType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referFormCtlType(HttpSession httpSession, String inPar, ResponseData responseData) {
		List<ComCode> comCodes;
		List<String> codeTypes = new ArrayList<String>();

		try {
			codeTypes.add("C0001");
			comCodes = comCodeMapper.selectCombData(codeTypes);

			responseData.setData(comCodes);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

}
