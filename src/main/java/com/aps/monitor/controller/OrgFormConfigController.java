package com.aps.monitor.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.service.IOrgFormConfigService;

@Controller
public class OrgFormConfigController {
	@Resource(name = "orgFormConfigServiceImpl")
	private IOrgFormConfigService orgFormConfigService;

	/**
	 * 
	 * @Title: refOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refOrg(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = orgFormConfigService.selectComOrg(comOrg);

		return JsonUtil.writeResponseAsString(comOrgs);
	}

	/**
	 * 
	 * @Title: refForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refForm", method = RequestMethod.POST)
	@ResponseBody
	public String refForm(HttpSession session, @RequestParam("inf") String inpar) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = orgFormConfigService.selectComForm(comForm);

		return JsonUtil.writeResponseAsString(comForms);
	}

	/**
	 * 
	 * @Title: refOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String refOrgForm(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrgForm comOrgForm = new ComOrgForm();
		List<ComOrgForm> comOrgForms;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comOrgForm.setOrgId(requestRefPar.getIntegerPar("orgId"));
		comOrgForm.setFormId(requestRefPar.getIntegerPar("formId"));
		comOrgForms = orgFormConfigService.selectComOrgForm(comOrgForm);

		return JsonUtil.writeResponseAsString(comOrgForms);
	}

	/**
	 * 
	 * @Title: refFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String refFormRights(HttpSession session, @RequestParam("inf") String inpar) {
		ComFormRights comFormRights = new ComFormRights();
		List<ComFormRights> comFormRightss;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
		comFormRightss = orgFormConfigService.selectComFormRights(comFormRights);

		return JsonUtil.writeResponseAsString(comFormRightss);
	}

	/**
	 * 
	 * @Title: refOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String refOrgFormRights(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrgFormRights comOrgFormRights = new ComOrgFormRights();
		List<ComOrgFormRights> comOrgFormRightss;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comOrgFormRights.setFormId(requestRefPar.getIntegerPar("formId"));
		comOrgFormRights.setOrgId(requestRefPar.getIntegerPar("orgId"));
		comOrgFormRightss = orgFormConfigService.selectByCondition(comOrgFormRights);

		return JsonUtil.writeResponseAsString(comOrgFormRightss);
	}

	/**
	 * 
	 * @Title: refComCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refComCode", method = RequestMethod.POST)
	@ResponseBody
	public String refComCode(HttpSession session, @RequestParam("inf") String inpar) {
		ComCode comCode = new ComCode();
		List<ComCode> comCodes;

		comCode.setCodeType("C0001");
		comCodes = orgFormConfigService.selectComCode(comCode);

		return JsonUtil.writeResponseAsString(comCodes);
	}

	/**
	 * 
	 * @Title: mdyComOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.mdyComOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String mdyComOrgForm(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgForm comOrgForm;
		ComOrgFormRights comOrgFormRights;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comOrgForm = (ComOrgForm) JsonUtil.readValueAsObject(rowData, ComOrgForm.class);
				if (null != comOrgForm) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comOrgForm.setItime(now);
							comOrgForm.setIperson(personId);
							comOrgForm.setUtime(now);
							comOrgForm.setUperson(personId);
							orgFormConfigService.insertComOrgFormSelective(comOrgForm);
							break;
						case "U":
							break;
						case "D":
							orgFormConfigService.deleteComOrgFormByPrimaryKey(comOrgForm);
							comOrgFormRights = new ComOrgFormRights();

							comOrgFormRights.setOrgId(comOrgForm.getOrgId());
							comOrgFormRights.setFormId(comOrgForm.getFormId());

							orgFormConfigService.deleteByPrimaryKey(comOrgFormRights);
							break;
						default:
							break;
					}
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setCode(-100);
			responseData.setMessage(e.getMessage());
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyComOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.mdyComOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String mdyComOrgFormRights(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrgFormRights comOrgFormRights;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comOrgFormRights = (ComOrgFormRights) JsonUtil.readValueAsObject(rowData, ComOrgFormRights.class);
				if (null != comOrgFormRights) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
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
							orgFormConfigService.insertSelective(comOrgFormRights);
							break;
						case "U":
							comOrgFormRights.setUtime(now);
							comOrgFormRights.setUperson(personId);
							orgFormConfigService.updateByPrimaryKeySelective(comOrgFormRights);
							break;
						case "D":
							break;
						default:
							break;
					}
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setCode(-100);
			responseData.setMessage(e.getMessage());
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
