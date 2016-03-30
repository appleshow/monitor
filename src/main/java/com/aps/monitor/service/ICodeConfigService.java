package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface ICodeConfigService {

	void referCode(HttpSession session, String inpar, ResponseData responseData);

	void modifyCode(HttpSession session, String inpar, ResponseData responseData);

}
