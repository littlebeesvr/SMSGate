package com.zx.sms;

import java.util.List;

import org.marre.sms.SmsMessage;

import com.zx.sms.codec.cmpp.wap.LongMessageFrame;

import io.netty.channel.ChannelHandlerContext;

public interface LongSMSMessage<T> {
	public LongMessageFrame generateFrame();
	public T generateMessage(LongMessageFrame frame) throws Exception;
	public SmsMessage getSmsMessage();
	public boolean isReport();
	
	//下面两个方法用来保存合并短信前各个片断对应的消息
	public List<T> getFragments();
	public void addFragment(T fragment);
	
	//下面两个方法用来记录每个长短信分片从哪个连接上接收的
	public ChannelHandlerContext getContext();
	public void setContext(ChannelHandlerContext ctx);
}
