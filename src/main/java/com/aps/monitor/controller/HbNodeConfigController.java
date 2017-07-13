package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IHbNodeConfigService;

@Controller
public class HbNodeConfigController {
	@Resource(name = "hbNodeConfigServiceImpl")
	private IHbNodeConfigService hbNodeConfigService;
	private final int formId = 12;

	/**
	 * 
	 * @Title: referHbType
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
	@RequestMapping(value = "/hbNodeConfig.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbType(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		requestRefPar = StringUtil.conversionRequestReferData(requestRefPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbNodeConfigService.referHbType(httpSession, requestRefPar, responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @Title: referHbTypeItem
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
	@RequestMapping(value = "/hbNodeConfig.referHbTypeItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbTypeItem(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		requestRefPar = StringUtil.conversionRequestReferData(requestRefPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbTypeItem", responseData)) {
			hbNodeConfigService.referHbTypeItem(httpSession, requestRefPar, responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @Title: referHbNode
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
	@RequestMapping(value = "/hbNodeConfig.referHbNode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		requestRefPar = StringUtil.conversionRequestReferData(requestRefPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referHbNode", responseData)) {
			hbNodeConfigService.referHbNode(httpSession, requestRefPar, responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @Title: modifyHbNode
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
	@RequestMapping(value = "/hbNodeConfig.modifyHbNode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyHbNode(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		requestMdyPar = StringUtil.conversionRequestMdyData(requestMdyPar);
		if (CommUtil.isPermissoned(httpSession, formId, "modifyHbNode", responseData)) {
			hbNodeConfigService.modifyHbNode(httpSession, requestMdyPar, responseData);
		}

		return responseData;
	}
}
