package com.aps.monitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.RequestMdyPar;
import com.aps.monitor.comm.RequestRefPar;
import com.aps.monitor.comm.ResponseData;
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
	 * @see com.aps.monitor.service.IPageShowService#referAllForms(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referAllForms(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComForm comForm = new ComForm();
		List<ComForm> comForms;

		comForms = comFormMapper.selectCombData(comForm);

		responseData.setData(comForms);
	}

	/**
	 * 
	 * <p>
	 * Title: referHbType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPageShowService#referHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void referPageShow(HttpSession httpSession, String inPar, ResponseData responseData) {
		ComPageShow comPageShow = new ComPageShow();
		List<ComPageShow> comPageShows;
		RequestRefPar requestRefPar = JsonUtil.readRequestRefPar(inPar);
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
	 * <p>
	 * Title: modifyHbType
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param httpSession
	 * @param inPar
	 * @param responseData
	 * @see com.aps.monitor.service.IPageShowService#modifyHbType(javax.servlet.http.HttpSession,
	 *      java.lang.String, com.aps.monitor.comm.ResponseData)
	 */
	@Override
	public void modifyPageShow(HttpSession httpSession, String inPar, ResponseData responseData) {
		int personId;
		boolean jsonParseException = false;
		String type;
		Date now = new Date();
		Map<String, String> rowData;
		ComPageShow comPageShow;

		RequestMdyPar requestMdyPar = JsonUtil.readRequestMdyPar(inPar);
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
