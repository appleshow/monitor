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
	 * @Title: refForm
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
	@RequestMapping(value = "/hbTypeConfig.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public String referHbType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbTypeConfigService.referHbType(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyForm
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
	@RequestMapping(value = "/hbTypeConfig.modifyHbType", method = RequestMethod.POST)
	@ResponseBody
	public String modifyHbType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "modifyHbType", responseData)) {
			hbTypeConfigService.modifyHbType(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
