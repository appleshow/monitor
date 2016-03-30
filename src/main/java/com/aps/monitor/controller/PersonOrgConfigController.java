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
import com.aps.monitor.service.IPersonOrgConfigService;

/**
 * 
 * @ClassName: PersonOrgConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月25日 下午11:54:09
 * 
 * @since 1.0.0
 */
@Controller
public class PersonOrgConfigController {
	@Resource(name = "personOrgConfigServiceImpl")
	private IPersonOrgConfigService personOrgConfigService;

	/**
	 * 
	 * @Title: refComPerson
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComPerson", method = RequestMethod.POST)
	@ResponseBody
	public String refComPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personOrgConfigService.referComPerson(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refComOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refComOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personOrgConfigService.referComOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refComPersonOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refComPersonOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personOrgConfigService.referComPersonOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mydComPersonOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.mydComPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String mydComPersonOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		personOrgConfigService.modifyComPersonOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}
}
