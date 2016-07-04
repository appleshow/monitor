package com.aps.monitor.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbDataHisService {
	void refHbNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void refHbDataHis(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException;
}
