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
	 * @Title: referHbType
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbTypeItemConfig.referHbType", method = RequestMethod.POST)
	@ResponseBody
	public String referHbType(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbType", responseData)) {
			hbTypeItemConfigService.referHbType(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referHbTypeItem
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbTypeItemConfig.referHbTypeItem", method = RequestMethod.POST)
	@ResponseBody
	public String referHbTypeItem(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referHbTypeItem", responseData)) {
			hbTypeItemConfigService.referHbTypeItem(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyHbTypeItem
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbTypeItemConfig.modifyHbTypeItem", method = RequestMethod.POST)
	@ResponseBody
	public String modifyHbTypeItem(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyHbTypeItem", responseData)) {
			hbTypeItemConfigService.modifyHbTypeItem(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
