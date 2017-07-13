package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IHbTypeItemConfigService {

	void referHbType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referHbTypeItem(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyHbTypeItem(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

}
