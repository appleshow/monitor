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
	 * @see com.aps.monitor.service.IHbTypeItemConfigService#referHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbType(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		responseData.setData(hbTypes);
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
	 * @see com.aps.monitor.service.IHbTypeItemConfigService#referHbTypeItem(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbTypeItem hbTypeItem = new HbTypeItem();
		List<HbTypeItem> hbTypeItems;
		PageInfo<HbTypeItem> pageInfo;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

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
	 * <p>
	 * Title: modifyHbTypeItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbTypeItemConfigService#modifyHbTypeItem(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyHbTypeItem(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		HbTypeItem hbTypeItem;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
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
