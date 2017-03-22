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
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.service.IMenuConfigService;

/**
 * 菜单管理
 * 
 * @ClassName: MenuConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:32:48
 * 
 * @since 1.0.0
 */
@Controller
public class MenuConfigController extends BaseController {
	@Resource(name = "menuConfigServiceImpl")
	private IMenuConfigService menuConfigService;
	private final int formId = 5;

	/**
	 * 
	 * @Title: referMenu
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
	@RequestMapping(value = "/menuConfig.referMenu", method = RequestMethod.POST)
	@ResponseBody
	public String referMenu(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referMenu", responseData)) {
			menuConfigService.referMenu(httpSession, inPar, responseData);
		}
		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: modifyMenu
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
	@RequestMapping(value = "/menuConfig.modifyMenu", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMenu(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "modifyMenu", responseData)) {
			menuConfigService.modifyMenu(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: referAllForms
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
	@RequestMapping(value = "/menuConfig.referAllForms", method = RequestMethod.POST)
	@ResponseBody
	public String referAllForms(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "referAllForms", responseData)) {
			menuConfigService.referAllForms(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
