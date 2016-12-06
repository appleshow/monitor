package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HBDataLatestMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HBDataLatest;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbDataCurService;

/**
 * 
 * @ClassName: HbDataCurServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年6月25日 下午12:48:09
 * 
 * @since 1.0.0
 */
@Service
public class HbDataCurServiceImpl implements IHbDataCurService {
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbTypeItemMapper hbTypeItemMapper;
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
		HbTypeItem hbTypeItem = new HbTypeItem();
		HbNode hbNode = new HbNode();
		List<HbType> hbTypes;
		List<HbTypeItem> hbTypeItems;
		List<HbNode> hbNodes;
		ResponseData responseDataJoin = new ResponseData();

		hbNode.setIperson((int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID));
		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		hbNodes = hbNodeMapper.selectByPerson(hbNode);

		responseDataJoin.setData(hbTypes);
		responseDataJoin.setSubJoinResponseData(new ResponseData(hbTypeItems));
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

		hbDataLatest.setIperson((int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID));
		hbDataLatests = hbDatalatestMapper.selectByPerson(hbDataLatest);
		responseData.setData(hbDataLatests);
	}

}
