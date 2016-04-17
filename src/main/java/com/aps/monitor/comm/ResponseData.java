package com.aps.monitor.comm;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResponseData {
	private int code;
	private String message;
	private int rowCount;
	private int colCount;
	private long totalCount;
	private int pageSize;
	private int pageNumber;
	private List<?> data;
	private ObjectNode subJoin;

	public ResponseData() {

	}

	public ResponseData(Exception e) {
		setCode(-100);
		setMessage(e.getMessage());
	}

	public ResponseData(int errorCode, Exception e) {
		setCode(errorCode);
		setMessage(e.getMessage());
	}

	public ResponseData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseData(List<?> listData) {
		if (null == listData || listData.isEmpty()) {
			setCode(-1);
			setMessage("无法找到对应的资料...!!");
		} else {
			setCode(0);
			setRowCount(listData.size());
			setData(listData);
		}
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
	 * @Title: getRowCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @Title: setRowCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @Title: getColCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getColCount() {
		return colCount;
	}

	/**
	 * @Title: setColCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	/**
	 * @Title: getTotalCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @Title: setTotalCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @Title: getPagesize
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @Title: setPageSze
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setPageSze(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @Title: getPageNumber
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @Title: setPageNumber
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @Title: getData
	 * @Description:
	 * @return: List<T>
	 * @since 1.0.0
	 */

	public List<?> getData() {
		return data;
	}

	/**
	 * @Title: setData
	 * @Description:
	 * @return: List<T>
	 * @since 1.0.0
	 */
	public void setData(List<?> data) {
		if (null == data || data.isEmpty()) {
			setCode(-1);
			setMessage("无法找到对应的资料...!!");
		} else {
			setCode(0);
			setRowCount(data.size());
			this.data = data;
		}
	}

	/**
	 * 
	 * @Title: setData
	 * @Description: TODO
	 * @param: @param e
	 * @return: void
	 * @throws
	 * @since 1.0.0
	 */
	public void setData(Exception e) {
		setCode(-100);
		setMessage(e.getMessage());
	}

	/**
	 * 
	 * @Title: setData
	 * @Description: TODO
	 * @param: @param code
	 * @param: @param e
	 * @return: void
	 * @throws
	 * @since 1.0.0
	 */
	public void setData(int code, Exception e) {
		setCode(code);
		setMessage(e.getMessage());
	}

	/**
	 * @Title: getSubJoin
	 * @Description:
	 * @return: ObjectNode
	 * @since 1.0.0
	 */
	public ObjectNode getSubJoin() {
		return subJoin;
	}

	/**
	 * @Title: setSubJoin
	 * @Description:
	 * @return: ObjectNode
	 * @since 1.0.0
	 */
	public void setSubJoin(ObjectNode subJoin) {
		this.subJoin = subJoin;
	}

}
