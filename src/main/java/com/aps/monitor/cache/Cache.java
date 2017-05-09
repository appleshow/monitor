package com.aps.monitor.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.aps.monitor.data.Message;

/**
 * 
 * @ClassName: Cache
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月1日 下午8:12:15
 * 
 * @since 1.0.0
 */
public class Cache {
	private static final BlockingQueue<Message> cache;

	static {
		cache = new LinkedBlockingQueue<>();
	}

	private Cache() {

	}

	/**
	 * 
	 * @Title: getCache
	 * @Description: TODO
	 * @return BlockingQueue<Message>
	 * @author AppleShow
	 * @date 2017年4月14日 上午9:46:52
	 */
	public static BlockingQueue<Message> getCache() {
		return cache;
	}

}
