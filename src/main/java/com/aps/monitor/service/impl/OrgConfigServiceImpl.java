package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgMapper;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.service.IOrgConfigService;

@Service
public class OrgConfigServiceImpl implements IOrgConfigService {
	@Resource
	private ComOrgMapper comOrgMapper;

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
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyOrg(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrg comOrg;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
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
