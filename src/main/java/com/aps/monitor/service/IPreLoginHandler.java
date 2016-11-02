package com.aps.monitor.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface IPreLoginHandler {

	/**
	 * 前置处理
	 */
	Map<?, ?> handle(HttpSession session) throws Exception;
}
