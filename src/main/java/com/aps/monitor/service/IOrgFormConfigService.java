package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.ResponseData;

public interface IOrgFormConfigService {

	void referOrg(HttpSession httpSession, String inPar, ResponseData responseData);

	void referForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void referOrgForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void referFormRights(HttpSession httpSession, String inPar, ResponseData responseData);

	void referOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData);

	void referCombCode(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyOrgForm(HttpSession httpSession, String inPar, ResponseData responseData);

	void modifyOrgFormRights(HttpSession httpSession, String inPar, ResponseData responseData);
}
