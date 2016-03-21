package com.aps.monitor.controller;

import java.util.ArrayList;
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
import com.aps.monitor.model.ComFormRight;
import com.aps.monitor.service.IFormConfigService;

@Controller
public class FormConfigController {
	@Resource(name = "formConfigServiceImpl")
	private IFormConfigService formConfigService;

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
	@RequestMapping(value = "/formConfig.refForm", method = RequestMethod.POST)
	@ResponseBody
	public String refForm(HttpSession session, @RequestParam("inf") String inpar) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comForm.setPrgroup(requestRefPar.getStringPar("prgroup"));
		comForm.setFormName(requestRefPar.getStringPar("formName"));
		comForms = formConfigService.selectFormByCondition(comForm);

		return JsonUtil.writeResponseAsString(comForms);
	}

	/**
	 * 
	 * @Title: mdyForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.mdyForm", method = RequestMethod.POST)
	@ResponseBody
	public String mdyForm(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComForm comForm;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comForm = (ComForm) JsonUtil.readValueAsObject(rowData, ComForm.class);
				if (null != comForm) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comForm.setItime(now);
							comForm.setIperson(personId);
							comForm.setUtime(now);
							comForm.setUperson(personId);
							formConfigService.insertFormSelective(comForm);
							break;
						case "U":
							formConfigService.updateFormBySelective(comForm);
							break;
						case "D":
							formConfigService.deleteFormByPrimaryKey(comForm.getFormId());
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
	 * @Title: refFormRight
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.refFormRight", method = RequestMethod.POST)
	@ResponseBody
	public String refFormRight(HttpSession session, @RequestParam("inf") String inpar) {
		ComFormRight comFormRight = new ComFormRight();
		List<ComFormRight> comFormRights;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comFormRight.setFormId(requestRefPar.getIntegerPar("formId"));
		comFormRights = formConfigService.selectFormRightByCondition(comFormRight);

		return JsonUtil.writeResponseAsString(comFormRights);
	}

	/**
	 * 
	 * @Title: mdyFormRight
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.mdyFormRight", method = RequestMethod.POST)
	@ResponseBody
	public String mdyFormRight(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComFormRight comFormRight;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comFormRight = (ComFormRight) JsonUtil.readValueAsObject(rowData, ComFormRight.class);
				if (null != comFormRight) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comFormRight.setItime(now);
							comFormRight.setIperson(personId);
							comFormRight.setUtime(now);
							comFormRight.setUperson(personId);
							formConfigService.insertFormRightSelective(comFormRight);
							break;
						case "U":
							formConfigService.updateFormRightBySelective(comFormRight);
							break;
						case "D":
							formConfigService.deleteFormRightByPrimaryKey(comFormRight);
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
	 * @Title: refFormCtlType
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.refFormCtlType", method = RequestMethod.POST)
	@ResponseBody
	public String refFormCtlType(HttpSession session, @RequestParam("inf") String inpar) {
		List<ComCode> comCodes;
		List<String> codeTypes = new ArrayList<String>();

		codeTypes.add("C0001");
		comCodes = formConfigService.selectFormCtlType(codeTypes);

		return JsonUtil.writeResponseAsString(comCodes);
	}
}
