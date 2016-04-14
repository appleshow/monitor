package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbTypeConfigService {

	void referHbType(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyHbType(HttpSession httpSession, String inPar, ResponseData responseData);

}
