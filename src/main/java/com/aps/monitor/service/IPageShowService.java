package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IPageShowService {

	void referAllForms(HttpSession httpSession, String inPar, ResponseData responseData);

	void referPageShow(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyPageShow(HttpSession httpSession, String inPar, ResponseData responseData);

}
