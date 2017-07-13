package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.HbTypeItemMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbType;
import com.aps.monitor.model.HbTypeItem;
import com.aps.monitor.service.IHbTypeItemConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HbTypeItemConfigServiceImpl implements IHbTypeItemConfigService {
	@Resource
	HbTypeMapper hbTypeMapper;
	@Resource
	HbTypeItemMapper hbTypeItemMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referHbType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		responseData.setData(hbTypes);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referHbTypeItem(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems;
		PageInfo<HbTypeItem> pageInfo;

		hbTypeItem.setTypeId(requestRefPar.getIntegerPar("typeId"));
		hbTypeItem.setItemId(requestRefPar.getStringPar("itemId"));
		hbTypeItem.setItemName(requestRefPar.getStringPar("itemName"));
		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		hbTypeItems = hbTypeItemMapper.selectByCondition(hbTypeItem);
		pageInfo = new PageInfo<HbTypeItem>(hbTypeItems);

		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(hbTypeItems);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyHbTypeItem(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		HbTypeItem hbTypeItem;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			hbTypeItem = (HbTypeItem) JsonUtil.readValueAsObject(rowData, HbTypeItem.class);
			if (null != hbTypeItem) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					hbTypeItem.setItime(now);
					hbTypeItem.setIperson(personId);
					hbTypeItem.setUtime(now);
					hbTypeItem.setUperson(personId);
					hbTypeItemMapper.insertSelective(hbTypeItem);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					hbTypeItem.setUtime(now);
					hbTypeItem.setUperson(personId);
					hbTypeItemMapper.updateByPrimaryKeySelective(hbTypeItem);
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					hbTypeItemMapper.deleteByPrimaryKey(hbTypeItem);

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
			responseData.setMessage("数据处理异常[jsonParseException]，请检查输入数据！");
		} else {
			responseData.setCode(0);
		}
	}
}
