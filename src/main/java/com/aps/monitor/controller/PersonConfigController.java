package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/personConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			personConfigService.referOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param inPar
	 * @return
	 */
	@RequestMapping(value = "/personConfig.referPerson", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referPerson(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPerson", responseData)) {
			personConfigService.referPerson(httpSession, JsonUtil.readRequestRefPar(inPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/personConfig.modifyPerson", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyPerson(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyPerson", responseData)) {
			personConfigService.modifyPerson(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
