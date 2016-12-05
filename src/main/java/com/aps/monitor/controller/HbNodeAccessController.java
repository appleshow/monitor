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
import com.aps.monitor.service.IHbNodeAccessService;

/**
 * 
 * @ClassName: HbNodeAccessController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年12月1日 下午10:23:51
 * 
 * @since 1.0.0
 */
@Controller
public class HbNodeAccessController {
	@Resource(name = "hbNodeAccessServiceImpl")
	private IHbNodeAccessService hbNodeAccessService;
	private final int formId = 18;

	/**
	 * 
	 * @Title: refOrg
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refOrg", method = RequestMethod.POST)
	public String refOrg(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refOrg", responseData)) {
			hbNodeAccessService.refOrg(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);

	}

	/**
	 * 
	 * @Title: refNode
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refNode", method = RequestMethod.POST)
	public String refNode(HttpSession httpSession, @RequestParam("inf") String inPar) {

		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refNode", responseData)) {
			hbNodeAccessService.refNode(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);

	}

	/**
	 * 
	 * @Title: refNodeAccess
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refNodeAccess", method = RequestMethod.POST)
	public String refNodeAccess(HttpSession httpSession, @RequestParam("inf") String inPar) {

		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refNodeAccess", responseData)) {
			hbNodeAccessService.refNodeAccess(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);

	}

	/**
	 * 
	 * @Title: modifyNodeAccess
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.modifyNodeAccess", method = RequestMethod.POST)
	public String modifyNodeAccess(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyNodeAccess", responseData)) {
			hbNodeAccessService.modifyNodeAccess(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);

	}
}
