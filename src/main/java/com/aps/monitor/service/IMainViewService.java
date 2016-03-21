package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComMenu;

public interface IMainViewService {

	List<ComMenu> selectPersonMenu(Integer personId);
}
