package com.aps.monitor.communication;

import java.net.InetSocketAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.api.AbstractIoFilter;
import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoFuture;
import org.apache.mina.api.IoSession;
import org.apache.mina.transport.nio.NioTcpClient;

import com.aps.monitor.comm.CommUtil;

/**
 * 
 * @ClassName: NioClient
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年11月7日 下午8:29:58
 * 
 * @since 1.0.0
 */
public class NioClient {
	private static final Logger LOG = LogManager.getLogger(NioClient.class);
	private NioTcpClient nioTcpClient;

	public NioClient() {
		nioTcpClient = new NioTcpClient();
	}

	/**
	 * 
	 * @Title: tryToConnectServer
	 * @Description: TODO
	 * @return boolean
	 * @throws:
	 * @since 1.0.0
	 */
	public boolean tryToConnectServer() {
		nioTcpClient.setFilters(new AbstractIoFilter() {
		});
		nioTcpClient.setIoHandler(new AbstractIoHandler() {
		});

		try {
			IoFuture<IoSession> ioFuture = nioTcpClient.connect(new InetSocketAddress("localhost", CommUtil.NIO_TCP_PORT));
			IoSession ioSession = ioFuture.get();
			Thread.sleep(1000);
			ioSession.close(true);
			ioFuture.cancel(true);
			nioTcpClient.disconnect();
			nioTcpClient = null;
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}
		return true;
	}
}
