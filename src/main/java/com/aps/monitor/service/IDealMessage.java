package com.aps.monitor.service;

import com.aps.monitor.data.Message;

/**
 * 处理消息
 * 
 * @ClassName: DealMessage
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年10月31日 下午10:34:56
 * 
 * @since 1.0.0
 */
public interface IDealMessage {
	void saveMessage(Message message);
}
