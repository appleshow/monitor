package com.aps.monitor.comm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class RequestMdyPar {
	private int parcnt;
	private List<Map<String, String>> inpar;

	/**
	 * 
	 * @Title: getType
	 * @Description: TODO
	 * @param: @param rowData
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public String getType(Map<String, String> rowData) {
		String type = "";

		if (rowData.containsKey(SystemProperty.REQUEST_SQL_DML_TYPE)) {
			type = rowData.get(SystemProperty.REQUEST_SQL_DML_TYPE);
			rowData.remove(SystemProperty.REQUEST_SQL_DML_TYPE);
		}

		return type;
	}

	/**
	 * 
	 * @Title: getPersonId
	 * @Description: TODO
	 * @param: @param session
	 * @param: @param now
	 * @param: @param rowData
	 * @param: @return
	 * @return: int
	 * @throws
	 * @since 1.0.0
	 */
	public int getPersonId(HttpSession session, Date now, Map<String, String> rowData) {
		int personId = (int) session.getAttribute(SystemProperty.SESSION_PERSON_ID);

		rowData.put("utime", DateUtil.formatString(now));
		rowData.put("uperson", String.valueOf(personId));

		return personId;
	}

	/**
	 * @Title: getParcnt
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getParcnt() {
		return parcnt;
	}

	/**
	 * @Title: setParcnt
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setParcnt(int parcnt) {
		this.parcnt = parcnt;
	}

	/**
	 * @Title: getInpar
	 * @Description:
	 * @return: List<Map<String,String>>
	 * @since 1.0.0
	 */

	public List<Map<String, String>> getInpar() {
		return inpar;
	}

	/**
	 * @Title: setInpar
	 * @Description:
	 * @return: List<Map<String,String>>
	 * @since 1.0.0
	 */
	public void setInpar(List<Map<String, String>> inpar) {
		this.inpar = inpar;
	}

}
