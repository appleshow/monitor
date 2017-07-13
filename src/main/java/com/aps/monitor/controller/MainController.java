package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class MainController extends BaseController {
	@Resource(name = "mainViewServiceImpl")
	private IMainViewService mainViewService;

	/**
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/main.personMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData personMenu(HttpSession httpSession) {
		ResponseData responseData = new ResponseData();

		mainViewService.referPersonMenu(httpSession, null, responseData);

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/main.updatePersonPSW", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData updatePersonPSW(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		mainViewService.modifyPersonPassword(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param url
	 * @return
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
