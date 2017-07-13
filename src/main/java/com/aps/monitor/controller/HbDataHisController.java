package com.aps.monitor.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IHbDataHisService;

/**
 * 
 * @ClassName: HbDataHisController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年7月4日 下午10:52:27
 * 
 * @since 1.0.0
 */
@Controller
public class HbDataHisController {
	@Resource(name = "hbDataHisServiceImpl")
	private IHbDataHisService hbDataHisService;
	private final int formId = 14;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/HbDataHisController.refHbNode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbNode(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbNode", responseData)) {
			hbDataHisService.refHbNode(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/HbDataHisController.refHbDataHis", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbDataHis(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataHis", responseData)) {
			hbDataHisService.refHbDataHis(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/HbDataHisController.refHbDataHisGrid", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refHbDataHisGrid(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataHisGrid", responseData)) {
			hbDataHisService.refHbDataHisGrid(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
