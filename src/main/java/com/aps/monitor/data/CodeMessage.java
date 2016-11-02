package com.aps.monitor.data;

/**
 * 消息处理 - 编码、解码
 * 
 * @ClassName: DealMessage
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年10月31日 下午10:27:56
 * 
 * @since 1.0.0
 */
public interface CodeMessage {
	Object encoding(Object message);

	Object decoding(Object message);
}
