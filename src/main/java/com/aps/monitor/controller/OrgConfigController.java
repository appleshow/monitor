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
import com.aps.monitor.service.IOrgConfigService;

@Controller
public class OrgConfigController {
	@Resource(name = "orgConfigServiceImpl")
	private IOrgConfigService orgConfigService;

	/**
	 * 
	 * @Title: refOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.refOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgConfigService.referOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.mdyOrg", method = RequestMethod.POST)
	@ResponseBody
	public String mdyOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgConfigService.modifyOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}
}
