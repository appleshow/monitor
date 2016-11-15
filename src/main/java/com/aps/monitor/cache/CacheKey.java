package com.aps.monitor.cache;

import java.io.Serializable;

/**
 * 
 * @ClassName: CacheKey
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月1日 下午8:11:54
 * 
 * @since 1.0.0
 */
public class CacheKey implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 8206431909716276456L;
	private Long key;

	public CacheKey() {
		this.key = System.currentTimeMillis();
	}

	/**
	 * 
	 * @Title: CacheKey @Description: TODO @param: @param key @throws
	 */
	public CacheKey(long key) {
		this.key = key;
	}

	/**
	 * 
	 * @Title: getKey
	 * @Description: TODO
	 * @return long
	 * @throws:
	 * @since 1.0.0
	 */
	public long getKey() {
		return this.key;
	}

	/**
	 * 
	 * <p>
	 * Title: equals
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param object
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;

		CacheKey other = (CacheKey) object;
		if (getKey() == other.getKey()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <p>
	 * Title: hashCode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
