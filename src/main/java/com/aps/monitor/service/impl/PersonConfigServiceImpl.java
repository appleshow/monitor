package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.dao.ComPersonOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.service.IPersonConfigService;

@Service
public class PersonConfigServiceImpl implements IPersonConfigService {
	@Resource
	private ComOrgMapper comOrgMapper;
	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComPersonOrgMapper comPersonOrgMapper;

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
	public void referPerson(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;

		comPerson.setUserId(requestRefPar.getStringPar("userId"));
		comPerson.setUserName(requestRefPar.getStringPar("userName"));
		comPersons = comPersonMapper.selectByCondition(comPerson);
		responseData.setData(comPersons);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyPerson(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId, returnValue = 0;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPerson comPerson;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comPerson = (ComPerson) JsonUtil.readValueAsObject(rowData, ComPerson.class);
			if (null != comPerson) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(), CommUtil.LOCK_WORD));
					comPerson.setItime(now);
					comPerson.setIperson(personId);
					comPerson.setUtime(now);
					comPerson.setUperson(personId);

					returnValue = comPersonMapper.insertSelective(comPerson);
					if (returnValue > 0) {
						ComPersonOrg comPersonOrg = new ComPersonOrg();

						comPersonOrg.setPersonId(comPerson.getPersonId());
						comPersonOrg.setOrgId(comPerson.getUserOrg());
						comPersonOrg.setPrflag(1);
						comPersonOrg.setPrtype("0");
						comPersonOrg.setIperson(comPerson.getIperson());
						comPersonOrg.setItime(comPerson.getItime());
						comPersonOrg.setIperson(comPerson.getIperson());
						comPersonOrg.setUtime(comPerson.getUtime());

						comPersonOrgMapper.insertSelective(comPersonOrg);
					}

					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					if ("1".equals(rowData.get("resPsw"))) {
						comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(), CommUtil.LOCK_WORD));
					}
					returnValue = comPersonMapper.updateByPrimaryKeySelective(comPerson);
					if (returnValue > 0) {
						ComPersonOrg comPersonOrg = new ComPersonOrg();
						comPersonOrg.setPersonId(comPerson.getPersonId());
						comPersonOrg.setPrtype("0");
						comPersonOrgMapper.deleteByPrimaryKey(comPersonOrg);

						comPersonOrg.setPersonId(comPerson.getPersonId());
						comPersonOrg.setOrgId(comPerson.getUserOrg());
						comPersonOrg.setPrflag(1);
						comPersonOrg.setPrtype("0");
						comPersonOrg.setIperson(comPerson.getIperson());
						comPersonOrg.setItime(comPerson.getItime());
						comPersonOrg.setIperson(comPerson.getIperson());
						comPersonOrg.setUtime(comPerson.getUtime());

						comPersonOrgMapper.insertSelective(comPersonOrg);
					}
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					ComPersonOrg comPersonOrg = new ComPersonOrg();

					comPersonOrg.setPersonId(comPerson.getPersonId());
					comPersonOrgMapper.deleteByPrimaryKey(comPersonOrg);
					comPersonMapper.deleteByPrimaryKey(comPerson.getPersonId());

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
