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
import com.aps.monitor.service.IPersonOrgConfigService;

/**
 * 用户组织管理
 * 
 * @ClassName: PersonOrgConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月25日 下午11:54:09
 * 
 * @since 1.0.0
 */
@Controller
public class PersonOrgConfigController extends BaseController {
	@Resource(name = "personOrgConfigServiceImpl")
	private IPersonOrgConfigService personOrgConfigService;
	private final int formId = 7;

	/**
	 * 
	 * @Title: referComPerson
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.referPerson", method = RequestMethod.POST)
	@ResponseBody
	public String referPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPerson", responseData)) {
			personOrgConfigService.referPerson(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referComOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public String referOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			personOrgConfigService.referOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referComPersonOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.referPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String referPersonOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPersonOrg", responseData)) {
			personOrgConfigService.referPersonOrg(httpSession, inPar, responseData);
		}
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
	@RequestMapping(value = "/personOrgConfig.modifyPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String modifyPersonOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyPersonOrg", responseData)) {
			personOrgConfigService.modifyPersonOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
