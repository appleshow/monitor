package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.HbType;
import com.aps.monitor.service.IHbTypeConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @ClassName: HbTypeConfigServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年4月12日 下午10:59:52
 * 
 * @since 1.0.0
 */
@Service
public class HbTypeConfigServiceImpl implements IHbTypeConfigService {
	@Resource
	private HbTypeMapper hbTypeMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbTypeConfigService#referHbType(HttpSession,
	 *      RequestRefPar, ResponseData)
	 */
	@Override
	public void referHbType(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;
		PageInfo<HbType> pageInfo;

		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		hbTypes = hbTypeMapper.selectByCondition(hbType);
		pageInfo = new PageInfo<HbType>(hbTypes);

		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(hbTypes);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbTypeConfigService#modifyHbType(HttpSession,
	 *      RequestMdyPar, ResponseData)
	 */
	@Override
	public void modifyHbType(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		HbType hbType;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			hbType = (HbType) JsonUtil.readValueAsObject(rowData, HbType.class);
			if (null != hbType) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					hbType.setItime(now);
					hbType.setIperson(personId);
					hbType.setUtime(now);
					hbType.setUperson(personId);
					hbTypeMapper.insertSelective(hbType);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					hbType.setUtime(now);
					hbType.setUperson(personId);
					hbTypeMapper.updateByPrimaryKeySelective(hbType);
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					hbTypeMapper.deleteByPrimaryKey(hbType.getTypeId());

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
