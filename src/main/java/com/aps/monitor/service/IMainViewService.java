package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IMainViewService {

	void referPersonMenu(HttpSession httpSession, String inPar, ResponseData responseData);
}
