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
import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComMenuMapper;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComMenu;
import com.aps.monitor.service.IMenuConfigService;

@Service
public class MenuConfigServiceImpl implements IMenuConfigService {
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComMenuMapper comMenuMapper;

	/**
	 * 
	 * <p>
	 * Title: referMenu
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IMenuConfigService#referMenu(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referMenu(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComMenu comMenu = new ComMenu();
		List<ComMenu> comMenus;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);

		comMenu.setFarMenuId(requestRefPar.getIntegerPar("farMenuId"));
		comMenu.setMenuName(requestRefPar.getStringPar("menuName"));
		comMenus = comMenuMapper.selectByCondition(comMenu);

		responseData.setData(comMenus);
	}

	/**
	 * 
	 * <p>
	 * Title: modifyMenu
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IMenuConfigService#modifyMenu(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyMenu(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComMenu comMenu;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comMenu = (ComMenu) JsonUtil.readValueAsObject(rowData, ComMenu.class);
			if (null != comMenu) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
					case CommUtil.MODIFY_TYPE_INSERT:
						comMenu.setItime(now);
						comMenu.setIperson(personId);
						comMenu.setUtime(now);
						comMenu.setUperson(personId);
						comMenuMapper.insertSelective(comMenu);
						break;
					case CommUtil.MODIFY_TYPE_UPDATE:
						comMenuMapper.updateByPrimaryKeySelective(comMenu);
						break;
					case CommUtil.MODIFY_TYPE_DELETE:
						comMenuMapper.deleteByPrimaryKey(comMenu.getMenuId());
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

	/**
	 * 
	 * <p>
	 * Title: referAllForms
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IMenuConfigService#referAllForms(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referAllForms(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = comFormMapper.selectCombData(comForm);
		responseData.setData(comForms);
	}
}
