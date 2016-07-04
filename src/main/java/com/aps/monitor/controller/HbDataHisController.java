package com.aps.monitor.controller;

import java.text.ParseException;

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
	@RequestMapping(value = "/HbDataHisController.refHbNode", method = RequestMethod.POST)
	@ResponseBody
	public String refHbNode(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbNode", responseData)) {
			hbDataHisService.refHbNode(httpSession, inPar, responseData);
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
	 * @throws ParseException
	 * @throws @since
	 *             1.0.0
	 */
	@RequestMapping(value = "/HbDataHisController.refHbDataHis", method = RequestMethod.POST)
	@ResponseBody
	public String refHbDataHis(HttpSession httpSession, @RequestParam("inf") String inPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbDataHis", responseData)) {
			hbDataHisService.refHbDataHis(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}

}
