package com.aps.monitor.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.aps.monitor.comm.DateUtil;

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

	private static final long serialVersionUID = 1L;
	private long tryTimes;
	private String message;
	private String fromHost;
	private String lastTryDate;

	public Message() {
		this.message = null;
		this.fromHost = null;
		this.tryTimes = 0;
		this.lastTryDate = "";
	}

	public Message(String message) {
		this.message = message;
		this.tryTimes = 0;
		this.lastTryDate = "";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @Title: getMessageTailor
	 * @Description: TODO
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	public String getMessageTailor() {
		return message.trim().replaceAll("[\\n\\r]", "");
	}

	public long getTryTimes() {
		return tryTimes;
	}

	public void setFromHost(String fromHost) {
		this.fromHost = fromHost;
	}

	public String getFromHost() {
		return fromHost;
	}

	public String getLastTryDate() {
		return lastTryDate;
	}

	public void increaseTryTimes() {
		tryTimes++;
	}

	public void setLastTryDate() {
		lastTryDate = DateUtil.formatString(new Date());
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
		message = in.readUTF();
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
		out.writeUTF(message);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;

		Message other = (Message) object;
		if (getMessage().equals(other.getMessage())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return message.hashCode();
	}
}
