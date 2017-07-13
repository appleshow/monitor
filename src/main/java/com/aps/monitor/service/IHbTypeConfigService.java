package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IHbTypeConfigService {

	void referHbType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyHbType(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

}
