package com.aps.monitor.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IHbDataHisService {
	void refHbNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void refHbDataHis(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException;

	void refHbDataHisGrid(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException;

	void refHbDataHisGridContrast(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException;
}
