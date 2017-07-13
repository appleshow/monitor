package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface ICodeConfigService {

	void referCode(HttpSession session, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyCode(HttpSession session, RequestMdyPar requestMdyPar, ResponseData responseData);

}
