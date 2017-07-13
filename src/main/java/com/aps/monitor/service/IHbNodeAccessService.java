package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
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
	void refOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void refNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void refNodeAccess(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyNodeAccess(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);
}
