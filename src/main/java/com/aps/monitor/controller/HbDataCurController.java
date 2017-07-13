package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IHbDataCurService;

/**
 * 
 * @ClassName: HbDataCurController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年6月25日 下午12:57:47
 * 
 * @since 1.0.0
 */
@Controller
public class HbDataCurController {
	@Resource(name = "hbDataCurServiceImpl")
	private IHbDataCurService hbDataCurService;
	private final int formId = 13;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbDataCurController.refHbNode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbNode", responseData)) {
			hbDataCurService.refHbNode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbDataCurController.refHbDataLatest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbDataLatest(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataLatest", responseData)) {
			hbDataCurService.refHbDataLatest(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
