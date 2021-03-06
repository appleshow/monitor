package com.aps.monitor.communication;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.transport.nio.NioTcpServer;

import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.comm.DateUtil;

/**
 * 
 * @ClassName: NioServer
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年10月28日 下午10:14:44
 * 
 * @since 1.0.0
 */
public class NioServer {
	private static String serverStartTime;
	private static AtomicInteger port = new AtomicInteger(CommUtil.NIO_TCP_PORT);
	private static NioTcpServer NIOTCPSERVER = new NioTcpServer();
	private static final Logger LOG = LogManager.getLogger(NioServer.class);

	private NioServer() {

	}

	/**
	 * 
	 * @Title: start
	 * @Description: TODO
	 * @return boolean
	 * @throws:
	 * @since 1.0.0
	 */
	public static boolean start() {
		if (!NIOTCPSERVER.isActive()) {
			final SocketAddress socketAddress = new InetSocketAddress(port.get());
			NIOTCPSERVER.setFilters(new NioServerFilter());
			NIOTCPSERVER.setIoHandler(new NioServerIoHandler());
			NIOTCPSERVER.bind(socketAddress);

			serverStartTime = DateUtil.formatString(new Date(), DateUtil.SIMPLE_DATE_FORMAT1);
			LOG.info("Server started: " + port.get());
			return true;
		} else {
			LOG.info("Server has been started!");
			return false;
		}
	}

	/**
	 * 
	 * @Title: stop
	 * @Description: TODO
	 * @return boolean
	 * @throws:
	 * @since 1.0.0
	 */
	public static boolean stop() {
		if (NIOTCPSERVER != null) {
			try {
				NIOTCPSERVER.unbind();
			} catch (Exception e) {
				LOG.info(e);
			}
			NIOTCPSERVER = null;
			NIOTCPSERVER = new NioTcpServer();
			LOG.info("Server stoped!");

			return true;
		} else {
			LOG.info("Server has been stoped!");
			return false;
		}
	}

	/**
	 * 
	 * @Title: getPort
	 * @Description: TODO
	 * @return int
	 * @throws:
	 * @since 1.0.0
	 */
	public static int getPort() {
		return port.get();
	}

	/**
	 * 
	 * @Title: getServerStartTime
	 * @Description: TODO
	 * @return String
	 * @throws:
	 * @since 1.0.0
	 */
	public static String getServerStartTime() {
		if (NIOTCPSERVER.isActive()) {
			return serverStartTime;
		} else {
			return "----";
		}
	}
}
