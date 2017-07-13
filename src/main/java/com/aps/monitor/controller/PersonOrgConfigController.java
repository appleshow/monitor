package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/personOrgConfig.referPerson", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referPerson(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPerson", responseData)) {
			personOrgConfigService.referPerson(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @Title: referComOrg
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
	@RequestMapping(value = "/personOrgConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			personOrgConfigService.referOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @Title: referComPersonOrg
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
	@RequestMapping(value = "/personOrgConfig.referPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referPersonOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referPersonOrg", responseData)) {
			personOrgConfigService.referPersonOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}
		return responseData;
	}

	/**
	 * 
	 * @Title: mydComPersonOrg
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
	@RequestMapping(value = "/personOrgConfig.modifyPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyPersonOrg(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyPersonOrg", responseData)) {
			personOrgConfigService.modifyPersonOrg(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
