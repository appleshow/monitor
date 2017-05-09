package com.aps.monitor.cache;

/**
 * 
 * @ClassName: CacheOperation
 * @Description: TODO
 * @author AppleShow
 * @date 2017年4月13日 下午4:33:50
 *
 */
public class CacheOperation {
	private int total;
	private int success;
	private int failed;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CacheOperation() {
		this.total = 0;
		this.success = 0;
		this.failed = 0;
	}

	/**
	 * 
	 * @Title: operationSuccess
	 * @Description: TODO
	 * @return CacheOperation
	 * @author AppleShow
	 * @date 2017年4月13日 下午4:38:07
	 */
	public CacheOperation operationSuccess() {
		this.total++;
		this.success++;
		return this;
	}

	/**
	 * 
	 * @Title: operationFailed
	 * @Description: TODO
	 * @return CacheOperation
	 * @author AppleShow
	 * @date 2017年4月13日 下午4:38:14
	 */
	public CacheOperation operationFailed() {
		this.total++;
		this.failed++;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

}
