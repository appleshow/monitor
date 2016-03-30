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
import com.aps.monitor.service.IOrgFormConfigService;

@Controller
public class OrgFormConfigController {
	@Resource(name = "orgFormConfigServiceImpl")
	private IOrgFormConfigService orgFormConfigService;

	/**
	 * 
	 * @Title: refOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referOrg(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refForm", method = RequestMethod.POST)
	@ResponseBody
	public String refForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referForm(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String refOrgForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referOrgForm(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String refFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referFormRights(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String refOrgFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referOrgFormRights(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refComCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refComCode", method = RequestMethod.POST)
	@ResponseBody
	public String refComCode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.referComCode(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyComOrgForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.mdyComOrgForm", method = RequestMethod.POST)
	@ResponseBody
	public String mdyComOrgForm(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.modifyComOrgForm(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: mdyComOrgFormRights
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.mdyComOrgFormRights", method = RequestMethod.POST)
	@ResponseBody
	public String mdyComOrgFormRights(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		orgFormConfigService.modifyComOrgFormRights(httpSession, inPar, responseData);
		return JsonUtil.writeResponseAsString(responseData);
	}
}
