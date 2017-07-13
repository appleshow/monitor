package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.dao.ComPersonOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.service.IPersonOrgConfigService;

/**
 * 
 * @ClassName: PersonOrgConfigServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月31日 上午12:36:38
 * 
 * @since 1.0.0
 */
@Service
public class PersonOrgConfigServiceImpl implements IPersonOrgConfigService {
	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComPersonOrgMapper comPeronOrgMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referPerson(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;

		comPersons = comPersonMapper.selectByCondition(comPerson);
		responseData.setData(comPersons);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = comOrgMapper.selectByCondition(comOrg);
		responseData.setData(comOrgs);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referPersonOrg(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPersonOrg> comPersonOrgs;

		comPerson.setUserId(requestRefPar.getStringPar("userId"));
		comPerson.setUserName(requestRefPar.getStringPar("userName"));
		comPersonOrgs = comPeronOrgMapper.selectByCondition(comPerson);
		responseData.setData(comPersonOrgs);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyPersonOrg(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPersonOrg comPersonOrg;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comPersonOrg = (ComPersonOrg) JsonUtil.readValueAsObject(rowData, ComPersonOrg.class);
			if (null != comPersonOrg) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					comPersonOrg.setPrtype("1");
					comPersonOrg.setItime(now);
					comPersonOrg.setIperson(personId);
					comPersonOrg.setUtime(now);
					comPersonOrg.setUperson(personId);

					comPeronOrgMapper.insertSelective(comPersonOrg);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					comPersonOrg.setUtime(now);
					comPersonOrg.setUperson(personId);
					comPeronOrgMapper.updateByPrimaryKeySelective(comPersonOrg);
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					comPeronOrgMapper.deleteByPrimaryKey(comPersonOrg);
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
