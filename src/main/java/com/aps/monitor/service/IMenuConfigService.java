package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IMenuConfigService {
	void referMenu(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyMenu(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

	void referAllForms(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

}
