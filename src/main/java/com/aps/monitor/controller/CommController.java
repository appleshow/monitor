package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.service.ICommService;

@Controller
public class CommController extends BaseController {
	@Resource(name = "commServiceImpl")
	private ICommService commService;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/comm.referPageShow", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referPageShow(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		commService.referComPageShowByCondition(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);

		return responseData;
	}
}
