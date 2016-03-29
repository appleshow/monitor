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
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.comm.SystemProperty;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.IPersonConfigService;

@Controller
public class PersonConfigController {
	@Resource(name = "personConfigServiceImpl")
	private IPersonConfigService personConfigService;

	/**
	 * 
	 * @Title: refPerson
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personConfig.refPerson", method = RequestMethod.POST)
	@ResponseBody
	public String refPerson(HttpSession session, @RequestParam("inf") String inpar) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comPerson.setUserId(requestRefPar.getStringPar("userId"));
		comPerson.setUserName(requestRefPar.getStringPar("userName"));
		comPersons = personConfigService.selectByCondition(comPerson);

		return JsonUtil.writeResponseAsString(comPersons);
	}

	/**
	 * 
	 * @Title: mdyPerson
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personConfig.mdyPerson", method = RequestMethod.POST)
	@ResponseBody
	public String mdyPerson(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPerson comPerson;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comPerson = (ComPerson) JsonUtil.readValueAsObject(rowData, ComPerson.class);
				if (null != comPerson) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(),
									SystemProperty.LOCK_WORD));
							comPerson.setItime(now);
							comPerson.setIperson(personId);
							comPerson.setUtime(now);
							comPerson.setUperson(personId);
							personConfigService.insertSelective(comPerson);
							break;
						case "U":
							if ("1".equals(rowData.get("resPsw"))) {
								comPerson.setUserPsw(StringUtil.desEncryptStr(comPerson.getUserId(),
										SystemProperty.LOCK_WORD));
							}
							personConfigService.updateByPrimaryKeySelective(comPerson);
							break;
						case "D":
							personConfigService.deleteByPrimaryKey(comPerson.getPersonId());
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
