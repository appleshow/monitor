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
	 * @see com.aps.monitor.service.IHbTypeConfigService#referHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referHbType(HttpSession httpSession, String inPar, ResponseData responseData) {
		HbType hbType = new HbType();
		List<HbType> hbTypes;
		PageInfo<HbType> pageInfo;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
			hbTypes = hbTypeMapper.selectByCondition(hbType);
			pageInfo = new PageInfo<HbType>(hbTypes);

			responseData.setTotalCount(pageInfo.getTotal());
			responseData.setData(hbTypes);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: modifyHbType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IHbTypeConfigService#modifyHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyHbType(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		HbType hbType;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
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
							hbTypeMapper.updateByPrimaryKeySelective(hbType);
							break;
						case CommUtil.MODIFY_TYPE_DELETE:
							hbTypeMapper.deleteByPrimaryKey(hbType.getTypeId());

							break;
						default:
							break;
					}
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

}
