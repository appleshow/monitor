package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbNodeConfigService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HbNodeConfigServiceImpl implements IHbNodeConfigService {
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbTypeItemMapper hbTypeItemMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;

	/**
	 * 
	 * <p>
	 * Title: referHbType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbNodeConfigService#referHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbType(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems;

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		responseData.setData(hbTypes);

		ObjectNode subJoin = JsonUtil.getObjectNodeInstance();
		subJoin.putArray("typeItems").addAll(JsonUtil.valueToArrayNode(hbTypeItems));
		responseData.setSubJoin(subJoin);
	}

	/**
	 * 
	 * <p>
	 * Title: referHbTypeItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbNodeConfigService#referHbTypeItem(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems;
		PageInfo<HbTypeItem> pageInfo;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		hbTypeItem.setTypeId(requestRefPar.getIntegerPar("typeId"));
		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		pageInfo = new PageInfo<HbTypeItem>(hbTypeItems);

		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(hbTypeItems);
	}

	/**
	 * 
	 * <p>
	 * Title: referHbNode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbNodeConfigService#referHbNode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbNode(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbNode hbNode = new HbNode();
		List<HbNode> hbNodes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		hbNode.setTypeId(requestRefPar.getIntegerPar("typeId"));
		hbNode.setNodeId(requestRefPar.getIntegerPar("nodeId"));
		hbNodes = hbNodeMapper.selectByCondition(hbNode);

		responseData.setData(hbNodes);
	}

	/**
	 * 
	 * <p>
	 * Title: modifyHbNode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbNodeConfigService#modifyHbNode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyHbNode(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		HbNode hbNode;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			hbNode = (HbNode) JsonUtil.readValueAsObject(rowData, HbNode.class);
			if (null != hbNode) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
					case CommUtil.MODIFY_TYPE_INSERT:
						hbNode.setItime(now);
						hbNode.setIperson(personId);
						hbNode.setUtime(now);
						hbNode.setUperson(personId);
						hbNodeMapper.insertSelective(hbNode);
						break;
					case CommUtil.MODIFY_TYPE_UPDATE:
						hbNode.setUtime(now);
						hbNode.setUperson(personId);
						hbNodeMapper.updateByPrimaryKeySelective(hbNode);
						break;
					case CommUtil.MODIFY_TYPE_DELETE:
						hbNodeMapper.deleteByPrimaryKey(hbNode.getNodeId());
						break;
					default:
						break;
				}
			} else {
				jsonParseException = true;
				break;
			}
		}

		if (jsonParseException) {
			responseData.setCode(-108);
			responseData.setMessage("数据处理异常，请检查输入数据！");
		} else {
			responseData.setCode(0);
		}
	}

}
