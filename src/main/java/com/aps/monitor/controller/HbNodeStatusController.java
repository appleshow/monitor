package com.aps.monitor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @return
	 */
	@RequestMapping(value = "/hbNodeStatusController.refNodeStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData refNodeStatus(HttpSession httpSession, @RequestBody RequestRefPar requestRefPar) {
		ResponseData responseData = new ResponseData();

		if (CommUtil.isPermissoned(httpSession, formId, "refNodeStatus", responseData)) {
			hbNodeStatusService.refNodeStatus(httpSession, StringUtil.conversionRequestReferData(requestRefPar), responseData);
		}

		return responseData;
	}
}
