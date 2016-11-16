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
 * @ClassName: HbDataContrastController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月17日 上午12:24:17
 * 
 * @since 1.0.0
 */
@Controller
public class HbDataContrastController {
	@Resource(name = "hbDataHisServiceImpl")
	private IHbDataHisService hbDataHisService;
	private final int formId = 15;

	/**
	 * 
	 * @Title: refHbDataHisGridContrast
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @return
	 * @throws ParseException
	 *             String
	 * @throws:
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/HbDataContrastController.refHbDataHisGridContrast", method = RequestMethod.POST)
	@ResponseBody
	public String refHbDataHisGridContrast(HttpSession httpSession, @RequestParam("inf") String inPar) throws ParseException {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refHbData", responseData)) {
			hbDataHisService.refHbDataHisGridContrast(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
