package com.aps.monitor.communication;

import org.apache.mina.api.IdleStatus;
import org.apache.mina.api.IoFilter;
import org.apache.mina.api.IoSession;
import org.apache.mina.filterchain.ReadFilterChainController;
import org.apache.mina.filterchain.WriteFilterChainController;
import org.apache.mina.session.WriteRequest;

public class NioServerFilter implements IoFilter {

	@Override
	public void messageReceived(IoSession paramIoSession, Object paramObject, ReadFilterChainController paramReadFilterChainController) {
		// TODO Auto-generated method stub
		paramReadFilterChainController.callReadNextFilter(paramObject);
	}

	@Override
	public void messageSent(IoSession paramIoSession, Object paramObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageWriting(IoSession paramIoSession, WriteRequest paramWriteRequest, WriteFilterChainController paramWriteFilterChainController) {
		// TODO Auto-generated method stub
		paramWriteFilterChainController.callWriteNextFilter(paramWriteRequest);;
	}

	@Override
	public void sessionClosed(IoSession paramIoSession) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession paramIoSession) {
		// TODO Auto-generated method stub

	}

}
