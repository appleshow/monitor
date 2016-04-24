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

	/**
	 * 
	 * <p>
	 * Title: referOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgConfigService#referOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = comOrgMapper.selectByCondition(comOrg);
		responseData.setData(comOrgs);
	}

	/**
	 * 
	 * <p>
	 * Title: modifyOrg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IOrgConfigService#modifyOrg(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyOrg(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrg comOrg;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
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
