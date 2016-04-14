package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.ComPageShowMapper;
import com.aps.monitor.model.ComPageShow;
import com.aps.monitor.service.ICommService;

/**
 * 
 * @ClassName: CommServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月13日 下午10:27:07
 * 
 * @since 1.0.0
 */
@Service
public class CommServiceImpl implements ICommService {
	@Resource
	private ComPageShowMapper comPageShowMapper;

	/**
	 * 
	 * <p>
	 * Title: referComPageShowByCondition
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param session
	 * @param inpar
	 * @param responseData
	 * @see com.aps.monitor.service.ICommService#referComPageShowByCondition(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referComPageShowByCondition(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComPageShow comPageShow = new ComPageShow();
		List<?> comPageShows;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comPageShow.setPageId(requestRefPar.getIntegerPar("pageId"));
			comPageShows = comPageShowMapper.selectByCondition(comPageShow);

			responseData.setData(comPageShows);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}

	}

}
