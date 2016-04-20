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
import com.aps.monitor.service.IPageShowService;

@Controller
public class PageShowController {
	@Resource(name = "pageShowServiceImpl")
	private IPageShowService pageShowService;
	private int formId = 9;

	/**
	 * 
	 * @Title: referAllForms
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/pageShow.referAllForms", method = RequestMethod.POST)
	@ResponseBody
	public String referAllForms(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referAllForms", responseData)) {
			pageShowService.referAllForms(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referPageShow
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/pageShow.referPageShow", method = RequestMethod.POST)
	@ResponseBody
	public String referPageShow(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPageShow", responseData)) {
			pageShowService.referPageShow(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyPageShow
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/pageShow.modifyPageShow", method = RequestMethod.POST)
	@ResponseBody
	public String modifyPageShow(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyPageShow", responseData)) {
			pageShowService.modifyPageShow(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
