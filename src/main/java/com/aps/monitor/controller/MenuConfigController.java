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
import com.aps.monitor.service.IMenuConfigService;

@Controller
public class MenuConfigController {
	@Resource(name = "menuConfigServiceImpl")
	private IMenuConfigService menuConfigService;

	/**
	 * 
	 * @Title: refMenu
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.refMenu", method = RequestMethod.POST)
	@ResponseBody
	public String refMenu(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		menuConfigService.referMenu(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyMenu
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.mdyMenu", method = RequestMethod.POST)
	@ResponseBody
	public String mdyMenu(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		menuConfigService.modifyMenu(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refAllForms
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.refAllForms", method = RequestMethod.POST)
	@ResponseBody
	public String refAllForms(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		menuConfigService.referAllForms(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}
}
