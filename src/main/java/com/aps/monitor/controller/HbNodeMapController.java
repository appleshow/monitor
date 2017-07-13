package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public ResponseData refHbNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbNode", responseData)) {
			hbNodeMapService.refHbNode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
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
	public ResponseData refHbDataLatest(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataLatest", responseData)) {
			hbNodeMapService.refHbDataLatest(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

}
