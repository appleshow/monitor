package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

/**
 * 
 * @ClassName: IHbNodeAccessService
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年12月1日 下午10:15:35
 * 
 * @since 1.0.0
 */
public interface IHbNodeAccessService {
	void refOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void refNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void refNodeAccess(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyNodeAccess(HttpSession httpSession, String inPar, ResponseData responseData);
}
