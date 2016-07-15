package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HBDataLatestMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HBDataLatest;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.service.IHbNodeMapService;

@Service
public class HbNodeMapServiceImpl implements IHbNodeMapService {
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;
	@Resource
	private HBDataLatestMapper hbDatalatestMapper;

	/**
	 * 
	 * <p>
	 * Title: refHbNode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbDataCurService#refHbNode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbNode(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbType hbType = new HbType();
		HbNode hbNode = new HbNode();
		List<HbType> hbTypes;
		List<HbNode> hbNodes;
		ResponseData responseDataJoin = new ResponseData();

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbNodes = hbNodeMapper.selectByCondition(hbNode);

		responseDataJoin.setData(hbTypes);
		responseData.setData(hbNodes);
		responseData.setSubJoinResponseData(responseDataJoin);
	}

	/**
	 * 
	 * <p>
	 * Title: refNbDataLatest
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbDataCurService#refHbDataLatest(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbDataLatest(HttpSession httpSession, String inPar, ResponseData responseData) {
		HBDataLatest hbDataLatest = new HBDataLatest();
		List<HBDataLatest> hbDataLatests;

		hbDataLatests = hbDatalatestMapper.selectByCondition(hbDataLatest);
		responseData.setData(hbDataLatests);
	}

}
