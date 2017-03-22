package com.aps.monitor.controller;

import java.text.ParseException;

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
import com.aps.monitor.service.IHbDataContrastService;

/**
 * 
 * @ClassName: HbDataContrastController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月17日 上午12:24:17
 * 
 * @since 1.0.0
 */
@Controller
public class HbDataContrastController {
	@Resource(name = "hbDataContrastServiceImpl")
	private IHbDataContrastService hbDataContrastService;
	private final int formId = 15;

	/**
	 * 
	 * @Title: referHbType
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbDataContrastController.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public String referHbType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbDataContrastService.referHbType(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referHbNode
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
	@RequestMapping(value = "/hbDataContrastController.referHbNode", method = RequestMethod.POST)
	@ResponseBody
	public String referHbNode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbNode", responseData)) {
			hbDataContrastService.referHbNode(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refHbDataHisGridContrast
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @return
	 * @throws ParseException
	 *             String
	 * @throws:
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbDataContrastController.refHbData", method = RequestMethod.POST)
	@ResponseBody
	public String refHbData(HttpSession httpSession, @RequestParam("inf") String inPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "refHbData", responseData)) {
			hbDataContrastService.refHbData(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
