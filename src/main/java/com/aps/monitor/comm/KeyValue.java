package com.aps.monitor.comm;

/**
 * 
 * @ClassName: KeyValue
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月3日 下午8:58:04
 * 
 * @since 1.0.0
 */
public class KeyValue {

	private String key;
	private String value;

	public KeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @Title: getKey
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getKey() {
		return key;
	}

	/**
	 * @Title: setKey
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @Title: getValue
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getValue() {
		return value;
	}

	/**
	 * @Title: setValue
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
