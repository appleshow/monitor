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
public class OrgFormConfigController {
	@Resource(name = "orgFormConfigServiceImpl")
	private IOrgFormConfigService orgFormConfigService;
	private final int formId = 6;

	/**
	 * 
	 * @Title: referOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public String referOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			orgFormConfigService.referOrg(httpSession, inPar, responseData);
		}
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referForm", method = RequestMethod.POST)
	@ResponseBody
	public String referForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referForm", responseData)) {
			orgFormConfigService.referForm(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String referOrgForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrgForm", responseData)) {
			orgFormConfigService.referOrgForm(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String referFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referFormRights", responseData)) {
			orgFormConfigService.referFormRights(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String referOrgFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrgFormRights", responseData)) {
			orgFormConfigService.referOrgFormRights(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referComCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.referCombCode", method = RequestMethod.POST)
	@ResponseBody
	public String referCombCode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referCombCode", responseData)) {
			orgFormConfigService.referCombCode(httpSession, inPar, responseData);
		}
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyComOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.modifyOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String modifyOrgForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrgForm", responseData)) {
			orgFormConfigService.modifyOrgForm(httpSession, inPar, responseData);
		}
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyComOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.modifyOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String modifyOrgFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrgFormRights", responseData)) {
			orgFormConfigService.modifyOrgFormRights(httpSession, inPar, responseData);
		}
		return JsonUtil.writeResponseAsString(responseData);
	}
}
