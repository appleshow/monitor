package com.aps.monitor.comm;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
	private int code;
	private String message;
	private int rowcount;
	private int colcount;
	private int totalcount;
	private int pagesize;
	private int pagenumber;
	private List<T> data = new ArrayList<T>();

	public ResponseData() {

	}

	public ResponseData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @Title: getCode
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getCode() {
		return code;
	}

	/**
	 * @Title: setCode
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @Title: getMessage
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * @Title: setMessage
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @Title: getRowcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getRowcount() {
		return rowcount;
	}

	/**
	 * @Title: setRowcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	/**
	 * @Title: getColcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getColcount() {
		return colcount;
	}

	/**
	 * @Title: setColcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setColcount(int colcount) {
		this.colcount = colcount;
	}

	/**
	 * @Title: getTotalcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getTotalcount() {
		return totalcount;
	}

	/**
	 * @Title: setTotalcount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	/**
	 * @Title: getPagesize
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getPagesize() {
		return pagesize;
	}

	/**
	 * @Title: setPagesize
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * @Title: getPagenumber
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getPagenumber() {
		return pagenumber;
	}

	/**
	 * @Title: setPagenumber
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}

	/**
	 * @Title: getData
	 * @Description:
	 * @return: List<T>
	 * @since 1.0.0
	 */

	public List<T> getData() {
		return data;
	}

	/**
	 * @Title: setData
	 * @Description:
	 * @return: List<T>
	 * @since 1.0.0
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

}
