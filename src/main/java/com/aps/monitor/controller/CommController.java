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
import com.aps.monitor.service.ICommService;

@Controller
public class CommController {
	@Resource(name = "commServiceImpl")
	private ICommService commService;

	/**
	 * 
	 * @Title: referCode
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param inPar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/comm.referPPageShow", method = RequestMethod.POST)
	@ResponseBody
	public String referPPageShow(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		commService.referComPageShowByCondition(httpSession, inPar, responseData);

		return JsonUtil.writeResponseAsString(responseData);
	}
}
