package com.aps.monitor.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IHbDataContrastService {
	void referHbType(HttpSession httpSession, String inPar, ResponseData responseData);

	void referHbNode(HttpSession httpSession, String inPar, ResponseData responseData);

	void refHbData(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException;
}
