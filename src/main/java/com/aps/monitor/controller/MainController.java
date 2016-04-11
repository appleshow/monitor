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
	 * @Title: updatePersonPSW
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/main.updatePersonPSW", method = RequestMethod.POST)
	@ResponseBody
	public String updatePersonPSW(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		mainViewService.modifyPersonPassword(httpSession, inPar, responseData);

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
	public String showPage(HttpSession httpSession, @RequestParam("url") String url) {
		if (null == httpSession.getAttribute(CommUtil.SESSION_PERSON_ID) || null == url || "".equals(url.trim())) {
			httpSession.invalidate();

			return "index.html";
		} else {
			return url;
		}
	}
}
