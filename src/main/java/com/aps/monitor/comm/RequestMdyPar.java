package com.aps.monitor.comm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class RequestMdyPar {
	private int parCount;
	private List<Map<String, String>> inPar;

	/**
	 * 
	 * @Title: getType
	 * @Description: TODO
	 * @param: @param
	 *             rowData
	 * @param: @return
	 * @return: String
	 * @throws @since
	 *             1.0.0
	 */
	public String getType(Map<String, String> rowData) {
		String type = "";

		if (rowData.containsKey(CommUtil.REQUEST_SQL_DML_TYPE)) {
			type = rowData.get(CommUtil.REQUEST_SQL_DML_TYPE);
			rowData.remove(CommUtil.REQUEST_SQL_DML_TYPE);
		}

		return type;
	}

	/**
	 * 
	 * @Title: getPersonId
	 * @Description: TODO
	 * @param: @param
	 *             session
	 * @param: @param
	 *             now
	 * @param: @param
	 *             rowData
	 * @param: @return
	 * @return: int
	 * @throws @since
	 *             1.0.0
	 */
	public int getPersonId(HttpSession session, Date now, Map<String, String> rowData) {
		int personId = (int) session.getAttribute(CommUtil.SESSION_PERSON_ID);

		rowData.put("utime", DateUtil.formatString(now, DateUtil.SIMPLE_DATE_FORMAT1));
		rowData.put("uperson", String.valueOf(personId));

		return personId;
	}

	/**
	 * @Title: getParCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getParCount() {
		return parCount;
	}

	/**
	 * @Title: setParCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setParCount(int parCount) {
		this.parCount = parCount;
	}

	/**
	 * @Title: getInPar
	 * @Description:
	 * @return: List<Map<String,String>>
	 * @since 1.0.0
	 */

	public List<Map<String, String>> getInPar() {
		return inPar;
	}

	/**
	 * @Title: setInPar
	 * @Description:
	 * @return: List<Map<String,String>>
	 * @since 1.0.0
	 */
	public void setInPar(List<Map<String, String>> inPar) {
		this.inPar = inPar;
	}

}
