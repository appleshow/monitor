package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IHbTypeItemConfigService;

/**
 * 
 * @ClassName: HbTypeItemConfigContoller
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月23日 下午7:49:50
 * 
 * @since 1.0.0
 */
@Controller
public class HbTypeItemConfigContoller extends BaseController {
	@Resource(name = "hbTypeItemConfigServiceImpl")
	private IHbTypeItemConfigService hbTypeItemConfigService;
	private final int formId = 11;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbTypeItemConfig.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbType(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbTypeItemConfigService.referHbType(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbTypeItemConfig.referHbTypeItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referHbTypeItem(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbTypeItem", responseData)) {
			hbTypeItemConfigService.referHbTypeItem(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/hbTypeItemConfig.modifyHbTypeItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyHbTypeItem(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyHbTypeItem", responseData)) {
			hbTypeItemConfigService.modifyHbTypeItem(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
