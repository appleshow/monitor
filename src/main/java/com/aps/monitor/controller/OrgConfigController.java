package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aps.monitor.service.IOrgConfigService;

/**
 * 组织管理
 * 
 * @ClassName: OrgConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月7日 下午8:34:01
 * 
 * @since 1.0.0
 */
@Controller
public class OrgConfigController extends BaseController {
	@Resource(name = "orgConfigServiceImpl")
	private IOrgConfigService orgConfigService;
	private final int formId = 2;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/orgConfig.referOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData referOrg(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "referOrg", responseData)) {
			orgConfigService.referOrg(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @return
	 */
	@RequestMapping(value = "/orgConfig.modifyOrg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData modifyOrg(HttpSession httpSession, @RequestBody RequestMdyPar requestMdyPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "modifyOrg", responseData)) {
			orgConfigService.modifyOrg(httpSession, StringUtil.conversionRequestMdyData(requestMdyPar), responseData);
		}

		return responseData;
	}
}
