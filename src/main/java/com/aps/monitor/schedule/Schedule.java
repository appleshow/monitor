package com.aps.monitor.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aps.monitor.cache.Cache;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.service.IDealMessage;

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

	public static boolean start() {
		if (status.get()) {
			LOG.error("Schedule has been started!");
			return false;
		}
		status.set(true);

		pullCacheData();
		LOG.info("Schedule is started!");

		return true;
	}

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
}
