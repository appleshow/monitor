package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbNodeConfigService {

	void referHbType(HttpSession httpSession, String inPar, ResponseData responseData);

	void referHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData);

	void referHbNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyHbNode(HttpSession httpSession, String inPar, ResponseData responseData);
}
