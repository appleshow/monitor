package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.StringUtil;
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
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 * @see com.aps.monitor.service.ICommService#referComPageShowByCondition(HttpSession,
	 *      RequestRefPar, ResponseData)
	 */
	@Override
	public void referComPageShowByCondition(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComPageShow comPageShow = new ComPageShow();
		List<?> comPageShows;

		comPageShow.setPageId(requestRefPar.getIntegerPar("pageId"));
		comPageShows = comPageShowMapper.selectByCondition(comPageShow);

		responseData.setData(comPageShows);
	}

}
