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
import com.aps.monitor.service.IOrgConfigService;

/**
 * 组织管理
 * 
 * @ClassName: OrgConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:34:01
 * 
 * @since 1.0.0
 */
@Controller
public class OrgConfigController {
	@Resource(name = "orgConfigServiceImpl")
	private IOrgConfigService orgConfigService;
	private final int formId = 2;

	/**
	 * 
	 * @Title: referOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public String referOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			orgConfigService.referOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyOrg
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.modifyOrg", method = RequestMethod.POST)
	@ResponseBody
	public String modifyOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrg", responseData)) {
			orgConfigService.modifyOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
