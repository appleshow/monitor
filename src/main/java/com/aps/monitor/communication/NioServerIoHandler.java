package com.aps.monitor.communication;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.api.IdleStatus;
import org.apache.mina.api.IoHandler;
import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;

import com.aps.monitor.cache.Cache;
import com.aps.monitor.data.Message;

/**
 * 
 * @ClassName: NioServerIoHandler
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年10月28日 下午10:10:28
 * 
 * @since 1.0.0
 */
public class NioServerIoHandler implements IoHandler {
	private static final Logger LOG = LogManager.getLogger(NioServerIoHandler.class);

	/**
	 * 
	 * <p>
	 * Title: exceptionCaught
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param paramIoSession
	 * @param paramException
	 * @see org.apache.mina.api.IoHandler#exceptionCaught(org.apache.mina.api.IoSession,
	 *      java.lang.Exception)
	 */
	@Override
	public void exceptionCaught(IoSession paramIoSession, Exception paramException) {
		LOG.error("Unexpected exception, we close the session : ", paramException);
		paramIoSession.close(true);
	}

	@Override
	public void messageReceived(IoSession paramIoSession, Object paramObject) {
		if (paramObject instanceof ByteBuffer) {
			Charset charset = Charset.forName("utf-8");
			CharsetDecoder charsetDecoder = charset.newDecoder();
			try {
				CharBuffer charBuffer = charsetDecoder.decode((ByteBuffer) paramObject);
				Message message = new Message();
				message.setFromHost(paramIoSession.getRemoteAddress().toString());
				message.setMessage(charBuffer.toString());
				
				Cache.put(message);
				LOG.debug("Received -> " + charBuffer.toString());
			} catch (CharacterCodingException e) {
				LOG.error(e);
			}
		}
	}

	@Override
	public void messageSent(IoSession paramIoSession, Object paramObject) {

	}

	@Override
	public void serviceActivated(IoService paramIoService) {
		LOG.info("Server is active.");
	}

	@Override
	public void serviceInactivated(IoService paramIoService) {
		LOG.info("Server is inactive.");
	}

	@Override
	public void sessionClosed(IoSession paramIoSession) {
		LOG.info("Session has been closed: " + paramIoSession);
	}

	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) {

	}

	@Override
	public void sessionOpened(IoSession paramIoSession) {
		LOG.info("New session opened: " + paramIoSession);
	}

}
