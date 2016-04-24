package com.aps.monitor.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.MD5Util;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.dao.ComMenuMapper;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.IMainViewService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MainViewServiceImpl implements IMainViewService {
	@Resource
	private ComMenuMapper comMenuMapper;
	@Resource
	private ComPersonMapper comPersonMapper;

	/**
	 * 
	 * <p>
	 * Title: referPersonMenu
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param session
	 * @param inpar
	 * @param responseData
	 * @see com.aps.monitor.service.IMainViewService#referPersonMenu(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referPersonMenu(HttpSession httpSession, String inPar, ResponseData responseData) {

		int personId = (int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID);
		ObjectNode personInf = JsonUtil.getObjectNodeInstance();

		responseData.setData(comMenuMapper.selectPersonMenu(personId));
		personInf.put(CommUtil.SESSION_USER_NAME, (String) httpSession.getAttribute(CommUtil.SESSION_USER_NAME));
		personInf.put(CommUtil.SESSION_USER_ID, (String) httpSession.getAttribute(CommUtil.SESSION_USER_ID));
		responseData.setSubJoin(personInf);
	}

	/**
	 * 
	 * <p>
	 * Title: modifyPersonPassword
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IMainViewService#modifyPersonPassword(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyPersonPassword(HttpSession httpSession, String inPar, ResponseData responseData) {

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
		ComPerson person = comPersonMapper.selectByPrimaryKey((int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID));
		String pswo = requestMdyPar.getInPar().get(0).get("pswo");
		String pswn = requestMdyPar.getInPar().get(0).get("pswn");

		if (null == person) {
			responseData.setCode(-100);
			responseData.setMessage("登陆超时，请重新登入!!");
			return;
		}

		if (!pswo.equals(MD5Util.getMD5String(StringUtil.desDecryptStr(person.getUserPsw(), CommUtil.LOCK_WORD)))) {
			responseData.setCode(-100);
			responseData.setMessage("您录入旧密码有误!!");
			return;
		}
		person.setUserPsw(StringUtil.desEncryptStr(pswn, CommUtil.LOCK_WORD));

		if (comPersonMapper.updateByPrimaryKeySelective(person) == 0) {
			responseData.setCode(-100);
			responseData.setMessage("用户已不存在!!");
			return;
		};

		responseData.setCode(0);
	}
}
