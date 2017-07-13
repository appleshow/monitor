package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IPageShowService {

	void referAllForms(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referPageShow(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyPageShow(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

}
