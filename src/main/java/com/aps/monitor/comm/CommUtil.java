package com.aps.monitor.comm;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.aps.monitor.model.ComOrgFormRights;

public class CommUtil {

	/*
	 * https://github.com/appleshow/monitor.git
	 */
	public final static String LOCK_WORD = "jmzfc369";
	public final static String SESSION_VERIFICATION = "verification";
	public final static String SESSION_PERSON_ID = "personId";
	public final static String SESSION_USER_ID = "userId";
	public final static String SESSION_USER_NAME = "userName";
	public final static String SESSION_USER_PERMISSIONS = "userPermissions";

	public final static String REQUEST_SQL_DML_TYPE = "_type";

	public final static String MODIFY_TYPE_INSERT = "I";
	public final static String MODIFY_TYPE_UPDATE = "U";
	public final static String MODIFY_TYPE_DELETE = "D";

	/**
	 * 
	 * @Title: updatePermissoned
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param personPermissions
	 * @return: void
	 * @throws
	 * @since 1.0.0
	 */
	public static void updatePermissoned(HttpSession httpSession, List<ComOrgFormRights> personPermissions) {
		httpSession.removeAttribute(SESSION_USER_PERMISSIONS);
		if (null != personPermissions) {
			httpSession.setAttribute(SESSION_USER_PERMISSIONS, personPermissions);
		}
	}

	/**
	 * 
	 * @Title: isPermissoned
	 * @Description: TODO
	 * @param: @param httpSession
	 * @param: @param formId
	 * @param: @param action
	 * @param: @param responseData
	 * @param: @return
	 * @return: boolean
	 * @throws
	 * @since 1.0.0
	 */
	public static boolean isPermissoned(HttpSession httpSession, int formId, String action, ResponseData responseData) {
		int permissionedCount = 0;
		boolean isPermissioined = false;
		Object sessionPermissions = httpSession.getAttribute(SESSION_USER_PERMISSIONS);

		if (null == httpSession.getAttribute(SESSION_PERSON_ID)) {
			responseData.setCode(-201);
			responseData.setMessage("未登录，您无法访问此功能！");

			isPermissioined = false;
		} else if (null == sessionPermissions) {
			responseData.setCode(-202);
			responseData.setMessage("权限不足，您无法访问此功能！");

			isPermissioined = false;
		} else {
			@SuppressWarnings("unchecked")
			List<ComOrgFormRights> personPermissions = (List<ComOrgFormRights>) sessionPermissions;

			for (ComOrgFormRights orgFormRights : personPermissions) {
				if (orgFormRights.getFormId() == formId && orgFormRights.getRightId().equals(action)) {
					if (1 == CommUtil.nvl(orgFormRights.getRea())) {
						isPermissioined = false;
						responseData.setCode(-203);
						responseData.setMessage("功能已被锁定，您无法访问此功能！");
						return isPermissioined;
					}
					permissionedCount += CommUtil.nvl(orgFormRights.getRel());
				}
			}

			if (0 == permissionedCount) {
				responseData.setCode(-204);
				responseData.setMessage("权限不足，您无法访问此功能！");

				isPermissioined = false;
			} else {
				isPermissioined = true;
			}
		}

		return isPermissioined;
	}

	/**
	 * 
	 * @Title: nvl
	 * @Description: TODO
	 * @param: @param value
	 * @param: @param defaule
	 * @param: @return
	 * @return: int
	 * @throws
	 * @since 1.0.0
	 */
	public static int nvl(Integer value, int defaule) {
		return null == value ? defaule : value;
	}

	/**
	 * 
	 * @Title: nvl
	 * @Description: TODO
	 * @param: @param value
	 * @param: @return
	 * @return: int
	 * @throws
	 * @since 1.0.0
	 */
	public static int nvl(Integer value) {
		return nvl(value, 0);
	}

	/**
	 * 
	 * @Title: nvl
	 * @Description: TODO
	 * @param: @param value
	 * @param: @param defaule
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static String nvl(String value, String defaule) {
		return null == value ? defaule : value;
	}

	/**
	 * 
	 * @Title: nvl
	 * @Description: TODO
	 * @param: @param value
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static String nvl(String value) {
		return nvl(value, "");
	}

}
