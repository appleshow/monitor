package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import org.springframework.stereotype.Service;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HBDataLatestMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.model.HBDataLatest;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbNodeMapService;

@Service
public class HbNodeMapServiceImpl implements IHbNodeMapService {
	@Resource
	private HbTypeItemMapper hbTypeItemMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;
	@Resource
	private HBDataLatestMapper hbDatalatestMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refHbNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbTypeItem hbTypeItem = new HbTypeItem();
		HbNode hbNode = new HbNode();
		HBDataLatest hbDataLatest = new HBDataLatest();
		List<HbTypeItem> hbTypeItems;
		List<HbNode> hbNodes;
		List<HBDataLatest> hbDataLatests;
		ResponseData responseHbTypeItem = new ResponseData();
		ResponseData responseDataLatest = new ResponseData();

		hbDataLatests = hbDatalatestMapper.selectAllLastOne(hbDataLatest);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		hbNode.setIperson((int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID));
		hbNodes = hbNodeMapper.selectByPerson(hbNode);

		responseDataLatest.setData(hbDataLatests);
		responseHbTypeItem.setData(hbTypeItems);
		responseHbTypeItem.setSubJoinResponseData(responseDataLatest);
		responseData.setData(hbNodes);
		responseData.setSubJoinResponseData(responseHbTypeItem);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refHbDataLatest(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HBDataLatest hbDataLatest = new HBDataLatest();
		List<HBDataLatest> hbDataLatests;

		hbDataLatests = hbDatalatestMapper.selectByCondition(hbDataLatest);
		responseData.setData(hbDataLatests);
	}

}
