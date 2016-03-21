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
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComMenu;
import com.aps.monitor.service.IMenuConfigService;

@Controller
public class MenuConfigController {
	@Resource(name = "menuConfigServiceImpl")
	private IMenuConfigService menuConfigService;

	/**
	 * 
	 * @Title: refMenu
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.refMenu", method = RequestMethod.POST)
	@ResponseBody
	public String refMenu(HttpSession session, @RequestParam("inf") String inpar) {
		ComMenu comMenu = new ComMenu();
		List<ComMenu> comMenus;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inpar);

		comMenu.setFarMenuId(requestRefPar.getIntegerPar("farMenuId"));
		comMenu.setMenuName(requestRefPar.getStringPar("menuName"));
		comMenus = menuConfigService.selectByCondition(comMenu);

		return JsonUtil.writeResponseAsString(comMenus);

	}

	/**
	 * 
	 * @Title: mdyMenu
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.mdyMenu", method = RequestMethod.POST)
	@ResponseBody
	public String mdyMenu(HttpSession session, @RequestParam("inf") String inpar) {
		int personId;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComMenu comMenu;
		ResponseData<Object> responseData = new ResponseData<Object>();

		try {
			RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inpar);
			for (int row = 0; row < requestMdyPar.getParcnt(); row++) {
				rowData = requestMdyPar.getInpar().get(row);
				type = requestMdyPar.getType(rowData);
				comMenu = (ComMenu) JsonUtil.readValueAsObject(rowData, ComMenu.class);
				if (null != comMenu) {
					personId = requestMdyPar.getPersonId(session, now, rowData);
					switch (type) {
						case "I":
							comMenu.setItime(now);
							comMenu.setIperson(personId);
							comMenu.setUtime(now);
							comMenu.setUperson(personId);
							menuConfigService.insertSelective(comMenu);
							break;
						case "U":
							menuConfigService.updateByPrimaryKeySelective(comMenu);
							break;
						case "D":
							menuConfigService.deleteByPrimaryKey(comMenu.getMenuId());
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

	/**
	 * 
	 * @Title: refAllForms
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param inpar
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/menuConfig.refAllForms", method = RequestMethod.POST)
	@ResponseBody
	public String refAllForms(HttpSession session, @RequestParam("inf") String inpar) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = menuConfigService.selectAllForms(comForm);

		return JsonUtil.writeResponseAsString(comForms);

	}
}
