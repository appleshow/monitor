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
import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.service.ICodeConfigService;

@Service
public class CodeConfigServiceImpl implements ICodeConfigService {
	@Resource
	private ComCodeMapper comCodeMapper;

	/**
	 * 
	 * <p>
	 * Title: referComCode
	 * </p>
	 * <p>
	 * Description: 查询代码表数据
	 * </p>
	 * 
	 * @param session
	 * @param inpar
	 * @param responseData
	 * @return
	 * @see com.aps.monitor.service.ICodeConfigService#referComCode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referCode(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComCode comCode = new ComCode();
		List<?> comCodes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		comCode.setCodeType(requestRefPar.getStringPar("codeType"));
		comCode.setCodeName(requestRefPar.getStringPar("codeName"));
		comCodes = comCodeMapper.selectByCondition(comCode);

		responseData.setData(comCodes);
	}

	/**
	 * 
	 * <p>
	 * Title: modifyComCode
	 * </p>
	 * <p>
	 * Description: 修改代码表数据
	 * </p>
	 * 
	 * @param session
	 * @param inpar
	 * @param responseData
	 * @return
	 * @see com.aps.monitor.service.ICodeConfigService#modifyComCode(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyCode(HttpSession session, String inpar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComCode comCode;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comCode = (ComCode) JsonUtil.readValueAsObject(rowData, ComCode.class);
			if (null != comCode) {
				personId = requestMdyPar.getPersonId(session, now, rowData);
				switch (type) {
					case CommUtil.MODIFY_TYPE_INSERT:
						comCode.setItime(now);
						comCode.setIperson(personId);
						comCode.setUtime(now);
						comCode.setUperson(personId);
						comCodeMapper.insertSelective(comCode);
						break;
					case CommUtil.MODIFY_TYPE_UPDATE:
						comCodeMapper.updateByPrimaryKeyMap(rowData);
						break;
					case CommUtil.MODIFY_TYPE_DELETE:
						comCodeMapper.deleteByPrimaryKey(comCode);
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
