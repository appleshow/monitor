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
import com.aps.monitor.service.ICodeConfigService;

/**
 * 
 * @ClassName: CodeConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月16日 下午11:05:37
 * 
 * @since 1.0.0
 */
@Controller
public class CodeConfigController {
	@Resource(name = "codeConfigServiceImpl")
	private ICodeConfigService codeConfigService;

	/**
	 * 
	 * @Title: refCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/codeConfig.refCode", method = RequestMethod.POST)
	@ResponseBody
	public String refCode(HttpSession session, @RequestParam("inf") String inpar) {
		ResponseData responseData = new ResponseData();

		codeConfigService.referCode(session, inpar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/codeConfig.mdyCode", method = RequestMethod.POST)
	@ResponseBody
	public String mdyCode(HttpSession session, @RequestParam("inf") String inpar) {
		ResponseData responseData = new ResponseData();

		codeConfigService.modifyCode(session, inpar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}
}
