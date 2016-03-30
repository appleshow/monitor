package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IPersonOrgConfigService {

	void referComPerson(HttpSession httpSession, String inPar, ResponseData responseData);

	void referComOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void referComPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyComPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData);
}
