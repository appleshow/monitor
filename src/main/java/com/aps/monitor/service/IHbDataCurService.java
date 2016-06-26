package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbDataCurService {
	void refHbNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void refNbDataLatest(HttpSession httpSession, String inPar, ResponseData responseData);
}
