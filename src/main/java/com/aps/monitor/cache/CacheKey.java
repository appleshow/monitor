package com.aps.monitor.cache;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	private static final long serialVersionUID = 1L;
	private Long key;

	public CacheKey() {
		this.key = System.currentTimeMillis();
	}

	public CacheKey(long key) {
		this.key = key;
	}

	public long getKey() {
		return this.key;
	}

	/**
	 * For Serializable readObject
	 * 
	 * @Title: readObject
	 * @Description: TODO
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             void
	 * @throws:
	 * @since 1.0.0
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		key = in.readLong();
	}

	/**
	 * For writeObject readObject
	 * 
	 * @Title: writeObject
	 * @Description: TODO
	 * @param out
	 * @throws IOException
	 *             void
	 * @throws:
	 * @since 1.0.0
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(key);
	}

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

	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
