package com.aps.monitor.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.service.IOrgFormConfigService;

@Controller
public class OrgFormConfigController {
	@Resource(name = "orgFormConfigServiceImpl")
	private IOrgFormConfigService orgFormConfigService;

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
	@RequestMapping(value = "/orgFormConfig.refOrg", method = RequestMethod.POST)
	@ResponseBody
	public String refOrg(HttpSession session, @RequestParam("inf") String inpar) {
		ComOrg comOrg = new ComOrg();
		List<ComOrg> comOrgs;

		comOrgs = orgFormConfigService.selectByCondition(comOrg);

		return JsonUtil.writeResponseAsString(comOrgs);
	}

	/**
	 * 
	 * @Title: refForm
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/orgFormConfig.refForm", method = RequestMethod.POST)
	@ResponseBody
	public String refForm(HttpSession session, @RequestParam("inf") String inpar) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = orgFormConfigService.selectByCondition(comForm);

		return JsonUtil.writeResponseAsString(comForms);
	}

}
