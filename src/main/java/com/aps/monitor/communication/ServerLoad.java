package com.aps.monitor.communication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aps.monitor.cache.Cache;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.schedule.Schedule;

/**
 * 
 * @ClassName: ServerLoad
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年10月28日 下午11:02:05
 * 
 * @since 1.0.0
 */
public class ServerLoad implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		NioServer.stop();
		Schedule.stop();
		Cache.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		NioServer.start();
		Schedule.start();
		CommUtil.initHbNodeCache();
	}

}
