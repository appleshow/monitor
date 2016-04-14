package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface ICommService {

	void referComPageShowByCondition(HttpSession httpSession, String inPar, ResponseData responseData);
}
