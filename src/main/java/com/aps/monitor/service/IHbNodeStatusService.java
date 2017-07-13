package com.aps.monitor.service;

import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;

/**
 * 站点状态 接口
 * 
 * @ClassName: IHbNodeStatusService
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月22日 下午9:33:28
 * 
 * @since 1.0.0
 */
public interface IHbNodeStatusService {
	/**
	 * 查询所有站点状态信息
	 * 
	 * @Title: refNodeStatus
	 * @Description: TODO
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	void refNodeStatus(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData);
}
