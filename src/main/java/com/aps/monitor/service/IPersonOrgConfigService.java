package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IPersonOrgConfigService {

	void referPerson(HttpSession httpSession, String inPar, ResponseData responseData);

	void referOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void referPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData);
}
