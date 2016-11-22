package com.aps.monitor.data;

import java.io.Serializable;
import java.util.Date;

import com.aps.monitor.comm.DateUtil;
import com.google.common.base.Strings;

/**
 * 
 * @ClassName: Message
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月1日 下午8:10:42
 * 
 * @since 1.0.0
 */
public class Message implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -7044588105720013576L;
	private int tryTimes;
	private String messageBody;
	private String receiveDate;
	private String fromHost;
	private String lastTryDate;

	public Message() {
	}

	/**
	 * @Title: getTryTimes
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getTryTimes() {
		return tryTimes;
	}

	/**
	 * @Title: getMessageBody
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @Title: setMessageBody
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * @Title: getReceiveDate
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @Title: setReceiveDate
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @Title: getFromHost
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getFromHost() {
		return fromHost;
	}

	/**
	 * @Title: setFromHost
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setFromHost(String fromHost) {
		this.fromHost = fromHost;
	}

	/**
	 * @Title: getLastTryDate
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getLastTryDate() {
		return lastTryDate;
	}

	/**
	 * @Title: setLastTryDate
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setLastTryDate(String lastTryDate) {
		this.lastTryDate = lastTryDate;
	}

	/**
	 * 
	 * @Title: getMessageTailor
	 * @Description: TODO
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	public String getMessageBodyTailor() {
		return messageBody.trim().replaceAll("[\\n\\r]", "");
	}

	/**
	 * 
	 * @Title: increaseTryTimes
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	public void increaseTryTimes() {
		tryTimes++;
	}

	/**
	 * 
	 * @Title: setLastTryDate
	 * @Description: TODO void
	 * @throws:
	 * @since 1.0.0
	 */
	public void setLastTryDateDefault() {
		lastTryDate = DateUtil.formatString(new Date(), DateUtil.SIMPLE_DATE_FORMAT1);
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

		Message other = (Message) object;
		if (messageBody.equals(other.getMessageBody()) && fromHost.equals(other.getFromHost()) && lastTryDate.equals(other.getLastTryDate())
				&& tryTimes == other.getTryTimes()) {
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
		return messageBody.hashCode() + (Strings.isNullOrEmpty(fromHost) ? 0 : fromHost.hashCode())
				+ (Strings.isNullOrEmpty(lastTryDate) ? 0 : lastTryDate.hashCode()) + tryTimes;
	}
}
