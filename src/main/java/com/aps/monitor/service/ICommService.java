package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface ICommService {

	void referComPageShowByCondition(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);
}
