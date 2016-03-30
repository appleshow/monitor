package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IOrgConfigService {
	void referOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyOrg(HttpSession httpSession, String inPar, ResponseData responseData);

}
