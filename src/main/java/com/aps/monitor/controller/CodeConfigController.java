package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param inPar
	 * @return
	 */
	@RequestMapping(value = "/codeConfig.referCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referCode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referCode", responseData)) {
			codeConfigService.referCode(httpSession, JsonUtil.readRequestRefPar(inPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/codeConfig.modifyCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyCode(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyCode", responseData)) {
			codeConfigService.modifyCode(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
