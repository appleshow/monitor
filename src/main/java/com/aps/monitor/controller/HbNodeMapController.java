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
import com.aps.monitor.service.IHbNodeMapService;

/**
 * 
 * @ClassName: HbNodeMapController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年7月15日 下午9:49:36
 * 
 * @since 1.0.0
 */
@Controller
public class HbNodeMapController {
	@Resource(name = "hbNodeMapServiceImpl")
	private IHbNodeMapService hbNodeMapService;
	private final int formId = 8;

	/**
	 * 
	 * @Title: refHbNode
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
	@RequestMapping(value = "/hbNodeMapController.refHbNode", method = RequestMethod.POST)
	@ResponseBody
	public String refHbNode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbNode", responseData)) {
			hbNodeMapService.refHbNode(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

	/**
	 * 
	 * @Title: refHbDataLatest
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
	@RequestMapping(value = "/hbNodeMapController.refHbDataLatest", method = RequestMethod.POST)
	@ResponseBody
	public String refHbDataLatest(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataLatest", responseData)) {
			hbNodeMapService.refHbDataLatest(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

}
