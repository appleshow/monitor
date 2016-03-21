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
import com.aps.monitor.comm.ResponseData;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.service.IOrgConfigService;

@Controller
public class OrgConfigController {
	@Resource(name = "orgConfigServiceImpl")
	private IOrgConfigService orgConfigService;

	/**
	 * 
	 * @Title: refOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.refOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refOrg(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = orgConfigService.selectByCondition(comOrg);

		return JsonUtil.writeResponseAsString(comOrgs);
	}

	/**
	 * 
	 * @Title: mdyOrg
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgConfig.mdyOrg", method = RequestMethod.POST)
	@ResponseBody
	public String mdyOrg(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComOrg comOrg;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comOrg = (ComOrg) JsonUtil.readValueAsObject(rowData, ComOrg.class);
				if (null != comOrg) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comOrg.setItime(now);
							comOrg.setIperson(personId);
							comOrg.setUtime(now);
							comOrg.setUperson(personId);
							orgConfigService.insertSelective(comOrg);
							break;
						case "U":
							orgConfigService.updateByPrimaryKeyMap(rowData);
							break;
						case "D":
							orgConfigService.deleteByPrimaryKey(comOrg.getOrgId());
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
