package com.aps.monitor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.ResponseData;

/**
 * 
 * @ClassName: BaseController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月24日 上午11:11:33
 * 
 * @since 1.0.0
 */
public abstract class BaseController {
	/**
	 * 
	 * @Title: exception
	 * @Description: TODO
	 * @param: @param request
	 * @param: @param e
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@ExceptionHandler
	@ResponseBody
	public String exception(HttpServletRequest request, Exception e) {
		ResponseData responseData = new ResponseData();

		responseData.setData(e);

		return JsonUtil.writeResponseAsString(responseData);
	}

}
