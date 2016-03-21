package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	@Override
	public int deleteByPrimaryKey(Integer menuId) {
		return comMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insertSelective(ComMenu record) {
		return comMenuMapper.insertSelective(record);
	}

	@Override
	public List<ComMenu> selectByCondition(ComMenu record) {
		return comMenuMapper.selectByCondition(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ComMenu record) {
		return comMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ComForm> selectAllForms(ComForm record) {
		return comFormMapper.selectCombData(record);
	}

}
