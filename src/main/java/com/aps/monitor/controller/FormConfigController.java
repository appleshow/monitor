package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.service.IFormConfigService;

/**
 * 界面管理
 * 
 * @ClassName: FormConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:30:31
 * 
 * @since 1.0.0
 */
@Controller
public class FormConfigController {
	@Resource(name = "formConfigServiceImpl")
	private IFormConfigService formConfigService;
	private final int formId = 4;

	/**
	 * 
	 * @Title: refForm
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.referForm", method = RequestMethod.POST)
	@ResponseBody
	public String referForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referForm", responseData)) {
			formConfigService.referForm(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyForm
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.modifyForm", method = RequestMethod.POST)
	@ResponseBody
	public String modifyForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyForm", responseData)) {
			formConfigService.modifyForm(httpSession, inPar, responseData);
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
	@RequestMapping(value = "/formConfig.referFormRight", method = RequestMethod.POST)
	@ResponseBody
	public String referFormRight(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormRight", responseData)) {
			formConfigService.referFormRight(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyFormRight
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.modifyFormRight", method = RequestMethod.POST)
	@ResponseBody
	public String modifyFormRight(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyFormRight", responseData)) {
			formConfigService.modifyFormRight(httpSession, inPar, responseData);
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
	@RequestMapping(value = "/formConfig.referFormCtlType", method = RequestMethod.POST)
	@ResponseBody
	public String referFormCtlType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormCtlType", responseData)) {
			formConfigService.referFormCtlType(httpSession, inPar, responseData);
		}
		
		return JsonUtil.writeResponseAsString(responseData);
	}
}
