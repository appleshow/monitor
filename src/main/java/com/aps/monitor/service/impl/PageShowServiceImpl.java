package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.aps.monitor.comm.*;
import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComFormMapper;
import com.aps.monitor.dao.ComPageShowMapper;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComPageShow;
import com.aps.monitor.service.IPageShowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PageShowServiceImpl implements IPageShowService {
	@Resource
	private ComFormMapper comFormMapper;
	@Resource
	private ComPageShowMapper comPageShowMapper;

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referAllForms(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = comFormMapper.selectCombData(comForm);

		responseData.setData(comForms);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestRefPar
	 * @param responseData
	 */
	@Override
	public void referPageShow(HttpSession httpSession, RequestRefPar requestRefPar, ResponseData responseData) {
		ComPageShow comPageShow = new ComPageShow();
		List<ComPageShow> comPageShows;
		PageInfo<ComPageShow> pageInfo;

		comPageShow.setPageId(requestRefPar.getIntegerPar("pageId"));
		PageHelper.startPage(requestRefPar.getIntegerPar("pageNumber"), requestRefPar.getIntegerPar("pageSize"));
		comPageShows = comPageShowMapper.selectByCondition(comPageShow);
		pageInfo = new PageInfo<ComPageShow>(comPageShows);

		responseData.setTotalCount(pageInfo.getTotal());
		responseData.setData(comPageShows);
	}

	/**
	 * 
	 * @param httpSession
	 * @param requestMdyPar
	 * @param responseData
	 */
	@Override
	public void modifyPageShow(HttpSession httpSession, RequestMdyPar requestMdyPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPageShow comPageShow;

		for (int row = 0; row < requestMdyPar.getParCount(); row++) {
			rowData = requestMdyPar.getInPar().get(row);
			type = requestMdyPar.getType(rowData);
			comPageShow = (ComPageShow) JsonUtil.readValueAsObject(rowData, ComPageShow.class);
			if (null != comPageShow) {
				personId = requestMdyPar.getPersonId(httpSession, now, rowData);
				switch (type) {
				case CommUtil.MODIFY_TYPE_INSERT:
					comPageShow.setItime(now);
					comPageShow.setIperson(personId);
					comPageShow.setUtime(now);
					comPageShow.setUperson(personId);
					comPageShowMapper.insertSelective(comPageShow);
					break;
				case CommUtil.MODIFY_TYPE_UPDATE:
					comPageShowMapper.updateByPrimaryKeySelective(comPageShow);
					break;
				case CommUtil.MODIFY_TYPE_DELETE:
					comPageShowMapper.deleteByPrimaryKey(comPageShow);
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
