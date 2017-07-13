package com.aps.monitor.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IHbDataContrastService {
	void referHbType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referHbNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void refHbData(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException;
}
