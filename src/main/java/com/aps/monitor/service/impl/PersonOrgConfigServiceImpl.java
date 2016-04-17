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
import com.aps.monitor.comm.CommUtil;
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
	 * <p>
	 * Title: referComPerson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonOrgConfigService#referComPerson(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referPerson(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;

		try {
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
	 * Title: referComOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonOrgConfigService#referComOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		try {
			comOrgs = comOrgMapper.selectByCondition(comOrg);
			responseData.setData(comOrgs);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: referComPersonOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonOrgConfigService#referComPersonOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComPerson comPerson = new ComPerson();
		List<ComPersonOrg> comPersonOrgs;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		try {
			comPerson.setUserId(requestRefPar.getStringPar("userId"));
			comPerson.setUserName(requestRefPar.getStringPar("userName"));
			comPersonOrgs = comPeronOrgMapper.selectByCondition(comPerson);
			responseData.setData(comPersonOrgs);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: modifyComPersonOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPersonOrgConfigService#modifyComPersonOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyPersonOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPersonOrg comPersonOrg;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
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
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setData(e);
			throw (e);
		}
	}

}
