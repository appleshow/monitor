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
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.service.ICodeConfigService;

/**
 * 代码管理
 * 
 * @ClassName: CodeConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月16日 下午11:05:37
 * 
 * @since 1.0.0
 */
@Controller
public class CodeConfigController extends BaseController {
	@Resource(name = "codeConfigServiceImpl")
	private ICodeConfigService codeConfigService;

	private final int formId = 1;

	/**
	 * 
	 * @Title: referCode
	 * @Description: TODO
	 * @param: @param
	 *             httpSession
	 * @param: @param
	 *             inPar
	 * @param: @return
	 * @return: String
	 * @throws @since
	 *             1.0.0
	 */
	@RequestMapping(value = "/codeConfig.referCode", method = RequestMethod.POST)
	@ResponseBody
	public String referCode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referCode", responseData)) {
			codeConfigService.referCode(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyCode
	 * @Description: TODO
	 * @param: @param
	 *             httpSession
	 * @param: @param
	 *             inpar
	 * @param: @return
	 * @return: String
	 * @throws @since
	 *             1.0.0
	 */
	@RequestMapping(value = "/codeConfig.modifyCode", method = RequestMethod.POST)
	@ResponseBody
	public String modifyCode(HttpSession httpSession, @RequestParam("inf") String inpar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyCode", responseData)) {
			codeConfigService.modifyCode(httpSession, inpar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
