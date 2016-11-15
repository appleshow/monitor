package com.aps.monitor.cache;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.ehcache.Cache.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.PersistentUserManagedCache;
import org.ehcache.Status;
import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.LocalPersistenceService;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.ehcache.impl.persistence.DefaultLocalPersistenceService;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.data.Message;
import com.aps.monitor.service.IDealMessage;

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
	/**
	 * parameter for EHCache of on-heap, the faster tiers, the 'hottest' data
	 * <p>
	 * default value is 10 EntryUnit.ENTRIES
	 * <p>
	 */
	public static final long CACHE_ON_HEAP = 100;
	/**
	 * parameter for EHCache of off-heap, have to be serialized and deserialized
	 * - and is thus slower than heap
	 * <p>
	 * default value is 100 MemoryUnit.MB
	 * <p>
	 */
	public static final long CACHE_OFF_HEAP = 200;
	/**
	 * parameter for EHCache of on-disk, define a resource pool for the disk
	 * <p>
	 * default value is 500 MemoryUnit.MB
	 * <p>
	 * This parameter can be set in
	 */
	public static final long CACHE_ON_DISK = 1024;
	/**
	 * parameter for EHCache of life time, this means cache mappings will expire
	 * after a fixed duration following their creation
	 * <p>
	 * default value is 24*7 TimeUnit.HOURS
	 * <p>
	 */
	public static final long CACHE_LIFE_TIME = 24 * 7;
	private static final PersistentUserManagedCache<CacheKey, Message> cache;
	private static AtomicBoolean consuming = new AtomicBoolean(false);
	private static final Logger LOG = LogManager.getLogger(Cache.class);

	static {
		String cacheFilePath = CommUtil.SYS_PATH + "cache";
		System.out.println(cacheFilePath);
		File cacheFile = new File(cacheFilePath);

		if (!cacheFile.exists()) {
			cacheFile.mkdirs();
		}
		LocalPersistenceService persistenceService = new DefaultLocalPersistenceService(new DefaultPersistenceConfiguration(cacheFile));

		cache = UserManagedCacheBuilder.newUserManagedCacheBuilder(CacheKey.class, Message.class)
				.with(new UserManagedPersistenceContext<CacheKey, Message>("Message", persistenceService))
				.withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder().heap(CACHE_ON_HEAP, EntryUnit.ENTRIES).offheap(CACHE_OFF_HEAP, MemoryUnit.MB)
						.disk(CACHE_ON_DISK, MemoryUnit.MB, true))
				.withExpiry(Expirations.timeToLiveExpiration(Duration.of(CACHE_LIFE_TIME, TimeUnit.HOURS))).build(true);
	}

	private Cache() {

	}

	/**
	 * 
	 * @Title: pub
	 * @Description: TODO
	 * @param message
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	public static void put(Message message) {
		cache.put(new CacheKey(), message);
	}

	/**
	 * 
	 * @Title: put
	 * @Description: TODO
	 * @param cacheKey
	 * @param message
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	public static void put(CacheKey cacheKey, Message message) {
		cache.put(cacheKey, message);
	}

	/**
	 * 
	 * @Title: get
	 * @Description: TODO
	 * @param cacheKey
	 * @return Message
	 * @throws:
	 * @since 1.0.0
	 */
	public static Message get(CacheKey cacheKey) {
		return cache.get(cacheKey);
	}

	/**
	 * 
	 * @Title: forEach
	 * @Description: TODO
	 * @param action
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	public static void forEach(Consumer<? super Entry<CacheKey, Message>> action) {
		cache.forEach(action);
	}

	/**
	 * 
	 * @Title: getStatus
	 * @Description: TODO
	 * @return Status
	 * @throws:
	 * @since 1.0.0
	 */
	public static Status getStatus() {
		return cache.getStatus();
	}

	/**
	 * 
	 * @Title: getRuntimeConfiguration
	 * @Description: TODO
	 * @return CacheRuntimeConfiguration<CacheKey,Message>
	 * @throws:
	 * @since 1.0.0
	 */
	public static CacheRuntimeConfiguration<CacheKey, Message> getRuntimeConfiguration() {
		return cache.getRuntimeConfiguration();
	}

	/**
	 * 
	 * @Title: Consumer
	 * @Description: TODO
	 * @param codeMessage
	 * @param dealMessage
	 *            void
	 * @throws:
	 * @since 1.0.0
	 */
	public static void Consume(IDealMessage dealMessage) {
		if (consuming.get()) {
			LOG.debug("Consume is running!");
			return;
		}
		consuming.set(true);
		cache.forEach(item -> {
			CacheKey cacheKey = item.getKey();
			Message message = item.getValue();
			if (dealMessage.saveMessage(cacheKey, message)) {
				cache.remove(cacheKey);
			} else {
				message.setLastTryDateDefault();
				message.increaseTryTimes();
				put(cacheKey, message);
			}
		});
		consuming.set(false);
	}

	/**
	 * 
	 * @Title: close
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	public static void close() {
		cache.close();
		LOG.info("Cache colsed!");
	}

	/**
	 * 
	 * @Title: getInstance
	 * @Description: TODO
	 * @return PersistentUserManagedCache<CacheKey,Message>
	 * @throws:
	 * @since 1.0.0
	 */
	public static PersistentUserManagedCache<CacheKey, Message> getInstance() {
		return cache;
	}
}
