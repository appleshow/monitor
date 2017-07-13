package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

public interface IOrgFormConfigService {

	void referOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referForm(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referOrgForm(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referFormRights(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referOrgFormRights(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void referCombCode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);

	void modifyOrgForm(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);

	void modifyOrgFormRights(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData);
}
