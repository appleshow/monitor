package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refOrg", method = RequestMethod.POST)
	public ResponseData refOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refOrg", responseData)) {
			hbNodeAccessService.refOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;

	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refNode", method = RequestMethod.POST)
	public ResponseData refNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refNode", responseData)) {
			hbNodeAccessService.refNode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;

	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.refNodeAccess", method = RequestMethod.POST)
	public ResponseData refNodeAccess(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refNodeAccess", responseData)) {
			hbNodeAccessService.refNodeAccess(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;

	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "hbNodeAccessController.modifyNodeAccess", method = RequestMethod.POST)
	public ResponseData modifyNodeAccess(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyNodeAccess", responseData)) {
			hbNodeAccessService.modifyNodeAccess(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;

	}
}
