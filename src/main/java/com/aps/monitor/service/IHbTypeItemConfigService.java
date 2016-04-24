package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbTypeItemConfigService {

	void referHbType(HttpSession httpSession, String inPar, ResponseData responseData);

	void referHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData);

}
