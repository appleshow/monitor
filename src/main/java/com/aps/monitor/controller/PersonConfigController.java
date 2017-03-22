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
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.service.IPersonConfigService;

/**
 * 用户管理
 * 
 * @ClassName: PersonConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:36:17
 * 
 * @since 1.0.0
 */
@Controller
public class PersonConfigController extends BaseController {
	@Resource(name = "personConfigServiceImpl")
	private IPersonConfigService personConfigService;
	private final int formId = 3;

	/**
	 * 
	 * @Title: referOrg
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
	@RequestMapping(value = "/personConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public String referOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			personConfigService.referOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referPerson
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
	@RequestMapping(value = "/personConfig.referPerson", method = RequestMethod.POST)
	@ResponseBody
	public String referPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referPerson", responseData)) {
			personConfigService.referPerson(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyPerson
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
	@RequestMapping(value = "/personConfig.modifyPerson", method = RequestMethod.POST)
	@ResponseBody
	public String modifyPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "modifyPerson", responseData)) {
			personConfigService.modifyPerson(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
