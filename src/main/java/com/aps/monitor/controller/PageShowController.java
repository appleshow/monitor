package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IPageShowService;

@Controller
public class PageShowController extends BaseController {
	@Resource(name = "pageShowServiceImpl")
	private IPageShowService pageShowService;
	private int formId = 9;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/pageShow.referAllForms", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referAllForms(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referAllForms", responseData)) {
			pageShowService.referAllForms(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/pageShow.referPageShow", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referPageShow(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPageShow", responseData)) {
			pageShowService.referPageShow(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/pageShow.modifyPageShow", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyPageShow(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyPageShow", responseData)) {
			pageShowService.modifyPageShow(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
