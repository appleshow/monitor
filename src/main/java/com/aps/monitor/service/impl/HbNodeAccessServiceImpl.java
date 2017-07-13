package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.dao.ComResorceMapper;
import com.aps.monitor.dao.HbNodeMapper;
import com.aps.monitor.dao.HbTypeMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComResorce;
import com.aps.monitor.model.ComResorceExample;
import com.aps.monitor.model.HbNode;
import com.aps.monitor.model.HbType;
import com.aps.monitor.service.IHbNodeAccessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: HbNodeAccessServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年12月1日 下午10:21:16
 * 
 * @since 1.0.0
 */
@Service
public class HbNodeAccessServiceImpl implements IHbNodeAccessService {
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComResorceMapper comResourceMapper;
	@Resource
	private HbTypeMapper hbTypeMapper;
	@Resource
	private HbNodeMapper hbNodeMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		List<ComOrg> comOrgs;
		ComOrg comOrg = new ComOrg();

		comOrg.setFarOrgId(requestRefPar.getIntegerPar("farOrgId"));
		comOrgs = comOrgMapper.selectByFarOrgId(comOrg);

		responseData.setData(comOrgs);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refNode(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		HbType hbType = new HbType();
		HbNode hbNode = new HbNode();
		List<HbType> hbTypes;
		List<HbNode> hbNodes;

		hbTypes = hbTypeMapper.selectByCondition(hbType);
		hbNodes = hbNodeMapper.selectByCondition(hbNode);

		responseData.setData(hbNodes);
		responseData.setSubJoinResponseData(new ResponseData(hbTypes));
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void refNodeAccess(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		List<ComResorce> comResorces;
		ComResorceExample comResorceExample = new ComResorceExample();
		PageInfo<ComResorce> pageInfo;

		comResorceExample.createCriteria().andResourceTypeAEqualTo("NODE").andResourceTypeBEqualTo(requestRefPar.getStringPar("resourceTypeB"));
		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		comResorces = comResourceMapper.selectByExample(comResorceExample);
		pageInfo = new PageInfo<ComResorce>(comResorces);

		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(comResorces);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyNodeAccess(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		Date now = new Date();
		Map<String, String> rowData;
		ComResorce comResorce;
		boolean jsonParseException = false;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			comResorce = (ComResorce) JsonUtil.readValueAsObject(rowData, ComResorce.class);
			if (null != comResorce) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				if (comResorce.getResourceId() == -1) {
					comResorce.setResourceId(null);
					comResorce.setItime(now);
					comResorce.setIperson(personId);
					comResorce.setUtime(now);
					comResorce.setUperson(personId);
					comResourceMapper.insertSelective(comResorce);
				} else {
					comResorce.setUtime(now);
					comResorce.setUperson(personId);
					comResourceMapper.updateByPrimaryKeySelective(comResorce);
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
