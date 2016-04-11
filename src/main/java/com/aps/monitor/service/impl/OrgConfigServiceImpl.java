package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.service.IOrgConfigService;

@Service
public class OrgConfigServiceImpl implements IOrgConfigService {
	@Resource
	private ComOrgMapper comOrgMapper;

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

	@Override
	public void modifyOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrg comOrg;

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comOrg = (ComOrg) JsonUtil.readValueAsObject(rowData, ComOrg.class);
				if (null != comOrg) {
					personId = requestMdyPar.getPersonId(httpSession, now, rowData);
					switch (type) {
						case CommUtil.MODIFY_TYPE_INSERT:
							comOrg.setItime(now);
							comOrg.setIperson(personId);
							comOrg.setUtime(now);
							comOrg.setUperson(personId);
							comOrgMapper.insertSelective(comOrg);
							break;
						case CommUtil.MODIFY_TYPE_UPDATE:
							comOrgMapper.updateByPrimaryKeyMap(rowData);
							break;
						case CommUtil.MODIFY_TYPE_DELETE:
							comOrgMapper.deleteByPrimaryKey(comOrg.getOrgId());
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
