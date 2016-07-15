package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbNodeMapService {

	void refHbNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void refHbDataLatest(HttpSession httpSession, String inPar, ResponseData responseData);
}
