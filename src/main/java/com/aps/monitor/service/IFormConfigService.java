package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IFormConfigService {
	void referForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void referFormRight(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyFormRight(HttpSession httpSession, String inPar, ResponseData responseData);

	void referFormCtlType(HttpSession httpSession, String inPar, ResponseData responseData);
}
