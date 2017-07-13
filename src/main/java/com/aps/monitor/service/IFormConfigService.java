package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IFormConfigService {
	void referForm(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyForm(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

	void referFormRight(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyFormRight(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

	void referFormCtlType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);
}
