package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComMenuMapper;
import com.aps.monitor.model.ComMenu;
import com.aps.monitor.service.IMainViewService;

@Service
public class MainViewServiceImpl implements IMainViewService {

	@Resource
	private ComMenuMapper comMenuMapper;

	@Override
	public List<ComMenu> selectPersonMenu(Integer personId) {
		return comMenuMapper.selectPersonMenu(personId);
	}

}
