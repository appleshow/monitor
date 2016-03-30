package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IOrgFormConfigService {

	void referOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void referForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void referOrgForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void referFormRights(HttpSession httpSession, String inPar, ResponseData responseData);

	void referOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData);

	void referComCode(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyComOrgForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyComOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData);
}
