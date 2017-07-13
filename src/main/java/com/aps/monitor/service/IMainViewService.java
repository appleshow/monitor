package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IMainViewService {

	void referPersonMenu(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyPersonPassword(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

}
