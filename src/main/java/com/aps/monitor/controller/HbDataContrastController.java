package com.aps.monitor.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbDataContrastController.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbType(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbDataContrastService.referHbType(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbDataContrastController.referHbNode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbNode", responseData)) {
			hbDataContrastService.referHbNode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/hbDataContrastController.refHbData", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbData(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbData", responseData)) {
			hbDataContrastService.refHbData(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
