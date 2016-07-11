package com.aps.monitor.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HbDataModeMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbDataMode;
import com.aps.monitor.model.HbDataTable;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbDataHisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: HbDataHisServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年7月2日 下午11:11:22
 * 
 * @since 1.0.0
 */
@Service
public class HbDataHisServiceImpl implements IHbDataHisService {
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbTypeItemMapper hbTypeItemMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;
	@Resource
	private HbDataModeMapper hbDataModeMapper;

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
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		hbNode.setNodeMn(requestRefPar.getStringPar("nodeMN"));
		hbNodes = hbNodeMapper.selectByCondition(hbNode);

		responseDataJoin.setData(hbTypes);
		responseDataJoin.setSubJoinResponseData(new ResponseData(hbTypeItems));
		responseData.setData(hbNodes);
		responseData.setSubJoinResponseData(responseDataJoin);
	}

	/**
	 * 
	 * <p>
	 * Title: refHbDataHis
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @throws ParseException
	 * @see com.aps.monitor.service.IHbDataCurService#refHbDataLatest(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbDataHis(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		hbDataTable.setNodeId(requestRefPar.getIntegerPar("nodeId"));
		hbDataTable.setNodeMn(requestRefPar.getStringPar("nodeMn"));
		hbDataTable.setDataType(requestRefPar.getStringPar("dataType"));
		hbDataTable.setDateStr(dateFormat.parse(requestRefPar.getStringPar("dateStr")));
		hbDataTable.setDateEnd(dateFormat.parse(requestRefPar.getStringPar("dateEnd")));
		hbDataTable.setProperty0("hb_data_cur" + requestRefPar.getStringPar("nodeId"));
		hbDataModes = hbDataModeMapper.selectByCondition(hbDataTable);
		responseData.setData(hbDataModes);
	}

	/**
	 * 
	 * <p>
	 * Title: refHbDataHisGrid
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @throws ParseException
	 * @see com.aps.monitor.service.IHbDataHisService#refHbDataHisGrid(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void refHbDataHisGrid(HttpSession httpSession, String inPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageInfo<HbDataMode> pageInfo;

		hbDataTable.setNodeId(requestRefPar.getIntegerPar("nodeId"));
		hbDataTable.setNodeMn(requestRefPar.getStringPar("nodeMn"));
		hbDataTable.setDataType(requestRefPar.getStringPar("dataType"));
		hbDataTable.setDateStr(dateFormat.parse(requestRefPar.getStringPar("dateStr")));
		hbDataTable.setDateEnd(dateFormat.parse(requestRefPar.getStringPar("dateEnd")));
		hbDataTable.setProperty0("hb_data_cur" + requestRefPar.getStringPar("nodeId"));
		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		hbDataModes = hbDataModeMapper.selectByCondition(hbDataTable);
		pageInfo = new PageInfo<HbDataMode>(hbDataModes);
		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(hbDataModes);
	}

}
