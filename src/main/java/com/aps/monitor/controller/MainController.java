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
import com.aps.monitor.service.IMainViewService;

/**
 * 
 * @ClassName: MainController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月16日 下午11:02:24
 * 
 * @since 1.0.0
 */
@Controller
public class MainController {
	@Resource(name = "mainViewServiceImpl")
	private IMainViewService mainViewService;

	/**
	 * 
	 * @Title: personMenu
	 * @Description: TODO
	 * @param: @param session
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/main.personMenu", method = RequestMethod.POST)
	@ResponseBody
	public String personMenu(HttpSession httpSession) {
		ResponseData responseData = new ResponseData();

		mainViewService.referPersonMenu(httpSession, "", responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: showPage
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param url
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/main.showPage", method = RequestMethod.GET)
	public String showPage(HttpSession session, @RequestParam("url") String url) {
		return url;
	}
}
