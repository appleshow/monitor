package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IPersonConfigService {

	void referPerson(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyPerson(HttpSession httpSession, String inPar, ResponseData responseData);
}
