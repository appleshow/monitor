package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IHbDataCurService {
	void refHbNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void refHbDataLatest(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);
}
