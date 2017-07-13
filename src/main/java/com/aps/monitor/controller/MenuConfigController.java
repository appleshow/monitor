package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/menuConfig.referMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referMenu(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referMenu", responseData)) {
			menuConfigService.referMenu(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}
		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/menuConfig.modifyMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyMenu(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyMenu", responseData)) {
			menuConfigService.modifyMenu(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/menuConfig.referAllForms", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referAllForms(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referAllForms", responseData)) {
			menuConfigService.referAllForms(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
