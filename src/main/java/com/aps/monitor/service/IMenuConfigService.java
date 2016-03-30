package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IMenuConfigService {
	void referMenu(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyMenu(HttpSession httpSession, String inPar, ResponseData responseData);

	void referAllForms(HttpSession httpSession, String inPar, ResponseData responseData);

}
