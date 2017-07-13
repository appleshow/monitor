package com.aps.monitor.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

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
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refHbNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbType hbType = new HbType();
		HbTypeItem hbTypeItem = new HbTypeItem();
		HbNode hbNode = new HbNode();
		List<HbType> hbTypes;
		List<HbTypeItem> hbTypeItems;
		List<HbNode> hbNodes;
		ResponseData responseDataJoin = new ResponseData();

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		hbNode.setNodeMn(requestRefPar.getStringPar("nodeMN"));
		hbNode.setIperson((int) httpSession.getAttribute(CommUtil.SESSION_PERSON_ID));
		hbNodes = hbNodeMapper.selectByPerson(hbNode);

		responseDataJoin.setData(hbTypes);
		responseDataJoin.setSubJoinResponseData(new ResponseData(hbTypeItems));
		responseData.setData(hbNodes);
		responseData.setSubJoinResponseData(responseDataJoin);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 * @throws ParseException
	 * @see com.aps.monitor.service.IHbDataHisService#refHbDataHis(HttpSession,
	 *      RequestRefPar, ResponseData)
	 */
	@Override
	public void refHbDataHis(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes;
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
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 * @throws ParseException
	 */
	@Override
	public void refHbDataHisGrid(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes;
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

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 * @throws ParseException
	 */
	@Override
	public void refHbDataHisGridContrast(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) throws ParseException {
		HbDataTable hbDataTable = new HbDataTable();
		List<HbDataMode> hbDataModes = new ArrayList<>(), hbDataModesTmp;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageInfo<HbDataMode> pageInfo;
		String[] nodeIds = requestRefPar.getStringPar("nodeId").split(";");
		String[] nodeMns = requestRefPar.getStringPar("nodeMn").split(";");
		int totalCount = 0;

		for (int index = 0; index < nodeIds.length; index++) {
			hbDataTable.setNodeId(Integer.parseInt(nodeIds[index]));
			hbDataTable.setNodeMn(nodeMns[index]);
			hbDataTable.setDataType(requestRefPar.getStringPar("dataType"));
			hbDataTable.setDateStr(dateFormat.parse(requestRefPar.getStringPar("dateStr")));
			hbDataTable.setDateEnd(dateFormat.parse(requestRefPar.getStringPar("dateEnd")));
			hbDataTable.setProperty0("hb_data_cur" + nodeIds[index]);
			PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize") / nodeIds.length);
			hbDataModesTmp = hbDataModeMapper.selectByCondition(hbDataTable);
			pageInfo = new PageInfo<HbDataMode>(hbDataModesTmp);
			totalCount += pageInfo.getTotal();
			hbDataModes.addAll(hbDataModesTmp);
		}
		responseData.setTotalCount(totalCount);
		responseData.setData(hbDataModes.stream().sorted((hbDataMode1, hbDataMode2) -> hbDataMode1.getDataTime().before(hbDataMode2.getDataTime()) ? -1 : 1)
				.collect(Collectors.toList()));
	}

}
