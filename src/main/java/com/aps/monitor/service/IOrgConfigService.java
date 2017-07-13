package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IOrgConfigService {
	void referOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyOrg(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

}
