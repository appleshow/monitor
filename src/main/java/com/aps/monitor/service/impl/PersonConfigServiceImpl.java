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
import com.aps.monitor.comm.SystemProperty;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.dao.ComPersonOrgMapper;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.service.IPersonConfigService;

@Service
public class PersonConfigServiceImpl implements IPersonConfigService {
	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComPersonOrgMapper comPersonOrgMapper;

	/**
	 * 
	 * <p>
	 * Title: referPerson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonConfigService#referPerson(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referPerson(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comPerson.setUserId(requestRefPar.getStringPar("userId"));
			comPerson.setUserName(requestRefPar.getStringPar("userName"));
			comPersons = comPersonMapper.selectByCondition(comPerson);
			responseData.setData(comPersons);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: modifyPerson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonConfigService#modifyPerson(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyPerson(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId, returnValue = 0;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPerson comPerson;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comPerson = (ComPerson) JsonUtil.readValueAsObject(rowData, ComPerson.class);
				if (null != comPerson) {
					personId = requestMdyPar.getPersonId(httpSession, now, rowData);
					switch (type) {
						case SystemProperty.MODIFY_TYPE_INSERT:
							comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(), SystemProperty.LOCK_WORD));
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
						case SystemProperty.MODIFY_TYPE_UPDATE:
							if ("1".equals(rowData.get("resPsw"))) {
								comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(), SystemProperty.LOCK_WORD));
							}
							comPersonMapper.updateByPrimaryKeySelective(comPerson);
							break;
						case SystemProperty.MODIFY_TYPE_DELETE:
							ComPersonOrg comPersonOrg = new ComPersonOrg();

							comPersonOrg.setPersonId(personId);
							comPersonOrgMapper.deleteByPrimaryKey(comPersonOrg);
							comPersonMapper.deleteByPrimaryKey(personId);

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
