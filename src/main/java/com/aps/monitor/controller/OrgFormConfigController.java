package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IOrgFormConfigService;

/**
 * 组织权限管理
 * 
 * @ClassName: OrgFormConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:34:16
 * 
 * @since 1.0.0
 */
@Controller
public class OrgFormConfigController extends BaseController {
	@Resource(name = "orgFormConfigServiceImpl")
	private IOrgFormConfigService orgFormConfigService;
	private final int formId = 6;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			orgFormConfigService.referOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}
		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referForm(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referForm", responseData)) {
			orgFormConfigService.referForm(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrgForm(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrgForm", responseData)) {
			orgFormConfigService.referOrgForm(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referFormRights", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referFormRights(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormRights", responseData)) {
			orgFormConfigService.referFormRights(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrgFormRights(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrgFormRights", responseData)) {
			orgFormConfigService.referOrgFormRights(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.referCombCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referCombCode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referCombCode", responseData)) {
			orgFormConfigService.referCombCode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.modifyOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyOrgForm(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrgForm", responseData)) {
			orgFormConfigService.modifyOrgForm(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}
		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/orgFormConfig.modifyOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyOrgFormRights(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrgFormRights", responseData)) {
			orgFormConfigService.modifyOrgFormRights(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}
		return responseData;
	}
}
