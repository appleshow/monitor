package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComMenu;

public interface IMenuConfigService {

	int deleteByPrimaryKey(Integer menuId);

	int insertSelective(ComMenu record);

	List<ComMenu> selectByCondition(ComMenu record);

	int updateByPrimaryKeySelective(ComMenu record);

	List<ComForm> selectAllForms(ComForm record);

}
