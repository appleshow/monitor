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
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.service.IHbNodeStatusService;

/**
 * 站点状态 控制器
 * 
 * @ClassName: HbNodeStatusController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月22日 下午11:00:44
 * 
 * @since 1.0.0
 */
@Controller
public class HbNodeStatusController {
	@Resource(name = "hbNodeStatusServiceImpl")
	private IHbNodeStatusService hbNodeStatusService;
	private final int formId = 16;

	/**
	 * 查询站点状态
	 * 
	 * @Title: refNodeStatus
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/hbNodeStatusController.refNodeStatus", method = RequestMethod.POST)
	@ResponseBody
	public String refNodeStatus(HttpSession httpSession, @RequestParam("inf") String inPar) {
		ResponseData responseData = new ResponseData();

		inPar = StringUtil.getConversionString(inPar);
		if (CommUtil.isPermissoned(httpSession, formId, "refNodeStatus", responseData)) {
			hbNodeStatusService.refNodeStatus(httpSession, inPar, responseData);
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
