package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.service.IFormConfigService;

@Controller
public class FormConfigController {
	@Resource(name = "formConfigServiceImpl")
	private IFormConfigService formConfigService;

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
	@RequestMapping(value = "/formConfig.refForm", method = RequestMethod.POST)
	@ResponseBody
	public String refForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		formConfigService.referForm(httpSession, inPar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyForm
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/formConfig.mdyForm", method = RequestMethod.POST)
	@ResponseBody
	public String mdyForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		formConfigService.modifyForm(httpSession, inPar, responseData);

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
	public String refFormRight(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		formConfigService.referFormRight(httpSession, inPar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
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
	public String mdyFormRight(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		formConfigService.modifyFormRight(httpSession, inPar, responseData);

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
	public String refFormCtlType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		formConfigService.referFormCtlType(httpSession, inPar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}
}
