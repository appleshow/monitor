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
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.service.IPersonOrgConfigService;

/**
 * 
 * @ClassName: PersonOrgConfigController
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月25日 下午11:54:09
 * 
 * @since 1.0.0
 */
@Controller
public class PersonOrgConfigController {
	@Resource(name = "personOrgConfigServiceImpl")
	private IPersonOrgConfigService personOrgConfigService;

	/**
	 * 
	 * @Title: refComPerson
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComPerson", method = RequestMethod.POST)
	@ResponseBody
	public String refComPerson(HttpSession session, @RequestParam("inf") String inpar) {
		ComPerson comPerson = new ComPerson();
		List<ComPerson> comPersons;

		comPersons = personOrgConfigService.selectComPersonByCondition(comPerson);

		return JsonUtil.writeResponseAsString(comPersons);
	}

	/**
	 * 
	 * @Title: refComCode
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refComOrg(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = personOrgConfigService.selectComOrgByCondition(comOrg);

		return JsonUtil.writeResponseAsString(comOrgs);
	}

	/**
	 * 
	 * @Title: refComPersonOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.refComPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refComPersonOrg(HttpSession session, @RequestParam("inf") String inpar) {
		ComPerson comPerson = new ComPerson();
		List<ComPersonOrg> comPersonOrgs;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comPerson.setUserId(requestRefPar.getStringPar("userId"));
		comPerson.setUserName(requestRefPar.getStringPar("userName"));
		comPersonOrgs = personOrgConfigService.selectByCondition(comPerson);

		return JsonUtil.writeResponseAsString(comPersonOrgs);
	}

	/**
	 * 
	 * @Title: mdyComOrgPerson
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/personOrgConfig.mydComPersonOrg", method = RequestMethod.POST)
	@ResponseBody
	public String mydComPersonOrg(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPersonOrg comPersonOrg;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comPersonOrg = (ComPersonOrg) JsonUtil.readValueAsObject(rowData, ComPersonOrg.class);
				if (null != comPersonOrg) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comPersonOrg.setItime(now);
							comPersonOrg.setIperson(personId);
							comPersonOrg.setUtime(now);
							comPersonOrg.setUperson(personId);
							personOrgConfigService.insertSelective(comPersonOrg);
							break;
						case "U":
							comPersonOrg.setUtime(now);
							comPersonOrg.setUperson(personId);
							personOrgConfigService.updateByPrimaryKeySelective(comPersonOrg);
							break;
						case "D":
							personOrgConfigService.deleteByPrimaryKey(comPersonOrg);
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
