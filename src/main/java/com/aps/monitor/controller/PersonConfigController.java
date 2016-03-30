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
import com.aps.monitor.service.IPersonConfigService;

@Controller
public class PersonConfigController {
	@Resource(name = "personConfigServiceImpl")
	private IPersonConfigService personConfigService;

	/**
	 * 
	 * @Title: refPerson
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personConfig.refPerson", method = RequestMethod.POST)
	@ResponseBody
	public String refPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personConfigService.referPerson(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyPerson
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personConfig.mdyPerson", method = RequestMethod.POST)
	@ResponseBody
	public String mdyPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personConfigService.modifyPerson(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}
}
