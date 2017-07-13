package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IHbTypeConfigService;

/**
 * 
 * @ClassName: HbTypeConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月12日 下午11:00:33
 * 
 * @since 1.0.0
 */
@Controller
public class HbTypeConfigController extends BaseController {
	@Resource(name = "hbTypeConfigServiceImpl")
	private IHbTypeConfigService hbTypeConfigService;
	private final int formId = 10;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbTypeConfig.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbType(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbTypeConfigService.referHbType(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/hbTypeConfig.modifyHbType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyHbType(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyHbType", responseData)) {
			hbTypeConfigService.modifyHbType(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
