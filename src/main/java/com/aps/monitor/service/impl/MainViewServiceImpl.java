package com.aps.monitor.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.SystemProperty;
import com.aps.monitor.dao.ComMenuMapper;
import com.aps.monitor.service.IMainViewService;

@Service
public class MainViewServiceImpl implements IMainViewService {
	@Resource
	private ComMenuMapper comMenuMapper;

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

		try {
			int personId = (int) httpSession.getAttribute(SystemProperty.SESSION_PERSON_ID);
			responseData.setData(comMenuMapper.selectPersonMenu(personId));
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}

	}

}
