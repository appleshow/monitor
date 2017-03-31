package com.aps.monitor.schedule;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aps.monitor.cache.Cache;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.DateUtil;
import com.aps.monitor.comm.JsonUtil;
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.communication.NioClient;
import com.aps.monitor.communication.NioServer;
import com.aps.monitor.service.IDealMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;

/**
 * 
 * @ClassName: Schedule
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月1日 下午9:58:02
 * 
 * @since 1.0.0
 */
public class Schedule {
	private static AtomicBoolean status = new AtomicBoolean(false);
	private static final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);
	private static final Logger LOG = LogManager.getLogger(Schedule.class);

	private Schedule() {

	}

	/**
	 * 
	 * @Title: start
	 * @Description: TODO
	 * @return boolean
	 * @throws:
	 * @since 1.0.0
	 */
	public static boolean start() {
		if (status.get()) {
			LOG.error("Schedule has been started!");
			return false;
		}
		status.set(true);

		pullCacheData();
		checkNioServer();
		checkNodeStatus();
		LOG.info("Schedule is started!");

		return true;
	}

	/**
	 * 
	 * @Title: stop
	 * @Description: TODO
	 * @return boolean
	 * @throws:
	 * @since 1.0.0
	 */
	public static boolean stop() {
		if (status.get()) {
			LOG.info("Schedule is stopping...");
			schedule.shutdown();
			LOG.info("Schedule is stopped!");

			return true;
		} else {
			LOG.error("Schedule has been stopped!");
			return false;
		}
	}

	/**
	 * 
	 * @Title: pullCacheData
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	private static void pullCacheData() {
		schedule.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Cache.Consume(CommUtil.getBean("dealMessage212", IDealMessage.class));
			}
		}, 30, 10, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @Title: checkNIOServer
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	private static void checkNioServer() {
		schedule.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				NioClient nioClient = new NioClient();
				if (!nioClient.tryToConnectServer()) {
					LOG.info("Try to connect server failed! Restart server ...");
					NioServer.stop();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					NioServer.start();
					LOG.info("Server has been restated!");
				} else {
					LOG.info("Server's status check successed!");
				}
			}
		}, 60, 120, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @Title: checkNodeStatus
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	private static void checkNodeStatus() {
		schedule.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				final Date nowDate = new Date();
				final String nowDateStr = DateUtil.formatString(nowDate, DateUtil.SIMPLE_DATE_FORMAT1);
				CommUtil.getHbNodeCache().forEach((mn, node) -> {
					if (!Strings.isNullOrEmpty(node.getProperty9())) {
						final Date checkDate = DateUtil.fromString(node.getProperty9(), DateUtil.SIMPLE_DATE_FORMAT1);
						final String checkDateStr = DateUtil.formatString(checkDate, DateUtil.SIMPLE_DATE_FORMAT1);
						int offline = 5;
						try {
							ObjectNode nodeAtr = JsonUtil.getObjectMapper().readValue(node.getNodeAtr(), ObjectNode.class);
							if (nodeAtr.has("offline")) {
								offline = nodeAtr.get("offline").asInt();
							}
						} catch (JsonParseException e) {
						} catch (JsonMappingException e) {
						} catch (IOException e) {
						}
						if ((nowDate.getTime() - checkDate.getTime()) / (1000 * 60) >= offline) {
							if (!StringUtil.isNullOrEmpty(node.getProperty8())) {
								if (!checkDateStr.equals(node.getProperty8())) {
									node.setPrflag(node.getPrflag() + 1);
									node.setProperty8(checkDateStr);
								}
							} else {
								node.setPrflag(1);
								node.setProperty8(checkDateStr);
							}
						} else {

						}
					} else {
						if (Strings.isNullOrEmpty(node.getProperty8())) {
							node.setPrflag(1);
							node.setProperty8(nowDateStr);
						}
					}
				});
			}
		}, 10, 10, TimeUnit.MINUTES);
	}
}
