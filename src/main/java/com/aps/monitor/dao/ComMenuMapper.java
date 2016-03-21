package com.aps.monitor.dao;

import java.util.List;

import com.aps.monitor.model.ComMenu;

public interface ComMenuMapper {
	int deleteByPrimaryKey(Integer menuId);

	int insert(ComMenu record);

	int insertSelective(ComMenu record);

	ComMenu selectByPrimaryKey(Integer menuId);

	List<ComMenu> selectByCondition(ComMenu record);

	int updateByPrimaryKeySelective(ComMenu record);

	int updateByPrimaryKey(ComMenu record);

	List<ComMenu> selectPersonMenu(Integer personId);

}