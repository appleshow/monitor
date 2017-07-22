package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class FormConfigController extends BaseController {
	@Resource(name = "formConfigServiceImpl")
	private IFormConfigService formConfigService;
	private final int formId = 4;

	/**
	 * 
	 * @param httpSession
	 * @param inPar
	 * @return
	 */
	@RequestMapping(value = "/formConfig.referForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referForm", responseData)) {
			formConfigService.referForm(httpSession, JsonUtil.readRequestRefPar(inPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/formConfig.modifyForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyForm(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyForm", responseData)) {
			formConfigService.modifyForm(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param inPar
	 * @return
	 */
	@RequestMapping(value = "/formConfig.referFormRight", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referFormRight(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormRight", responseData)) {
			formConfigService.referFormRight(httpSession, JsonUtil.readRequestRefPar(inPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/formConfig.modifyFormRight", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyFormRight(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyFormRight", responseData)) {
			formConfigService.modifyFormRight(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/formConfig.referFormCtlType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referFormCtlType(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormCtlType", responseData)) {
			formConfigService.referFormCtlType(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
