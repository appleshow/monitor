package com.aps.monitor.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.service.ICodeConfigService;

/**
 * 
 * @ClassName: CodeConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月16日 下午11:05:37
 * 
 * @since 1.0.0
 */
@Controller
public class CodeConfigController {
	@Resource(name = "codeConfigServiceImpl")
	private ICodeConfigService codeConfigService;

	/**
	 * 
	 * @Title: refCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/codeConfig.refCode", method = RequestMethod.POST)
	@ResponseBody
	public String refCode(HttpSession session, @RequestParam("inf") String inpar) {
		ComCode comCode = new ComCode();
		List<ComCode> comCodes;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comCode.setCodeType(requestRefPar.getStringPar("codeType"));
		comCode.setCodeName(requestRefPar.getStringPar("codeName"));
		comCodes = codeConfigService.selectByCondition(comCode);

		return JsonUtil.writeResponseAsString(comCodes);
	}

	/**
	 * 
	 * @Title: mdyCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/codeConfig.mdyCode", method = RequestMethod.POST)
	@ResponseBody
	public String mdyCode(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComCode comCode;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comCode = (ComCode) JsonUtil.readValueAsObject(rowData, ComCode.class);
				if (null != comCode) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comCode.setItime(now);
							comCode.setIperson(personId);
							comCode.setUtime(now);
							comCode.setUperson(personId);
							codeConfigService.insertSelective(comCode);
							break;
						case "U":
							codeConfigService.updateByPrimaryKeyMap(rowData);
							break;
						case "D":
							codeConfigService.deleteByPrimaryKey(comCode);
							break;
						default:
							break;
					}
				}
			}

			responseData.setCode(0);
		} catch (Exception e) {
			responseData.setCode(-100);
			responseData.setMessage(e.getMessage());
		}

		return JsonUtil.writeResponseAsString(responseData);
	}
}
