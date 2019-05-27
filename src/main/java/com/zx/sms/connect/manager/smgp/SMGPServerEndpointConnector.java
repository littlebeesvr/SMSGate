package com.zx.sms.connect.manager.smgp;

import java.util.concurrent.TimeUnit;

import com.zx.sms.common.GlobalConstance;
import com.zx.sms.connect.manager.AbstractServerEndpointConnector;
import com.zx.sms.connect.manager.EndpointEntity;
import com.zx.sms.session.smgp.SMGPSessionLoginManager;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

public class SMGPServerEndpointConnector extends AbstractServerEndpointConnector {

	public SMGPServerEndpointConnector(EndpointEntity e) {
		super(e);
	}

	@Override
	protected void doinitPipeLine(ChannelPipeline pipeline) {
		EndpointEntity entity = getEndpointEntity();
		pipeline.addLast(GlobalConstance.IdleCheckerHandlerName, new IdleStateHandler(0, 0, entity.getIdleTimeSec(), TimeUnit.SECONDS));
		pipeline.addLast("SmgpServerIdleStateHandler", GlobalConstance.smgpidleHandler);
		pipeline.addLast(SMGPCodecChannelInitializer.pipeName(), new SMGPCodecChannelInitializer());
		pipeline.addLast("sessionLoginManager", new SMGPSessionLoginManager(getEndpointEntity()));

	}

}
