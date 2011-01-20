package de.uniluebeck.itm.nettyrxtx.rup;


import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RUPPayloadExtractor extends SimpleChannelUpstreamHandler {

	private static final Logger log = LoggerFactory.getLogger(RUPPayloadExtractor.class);

	@Override
	public void messageReceived(final ChannelHandlerContext ctx, final MessageEvent e) throws Exception {
		RUPPacket rupPacket = (RUPPacket) e.getMessage();
		log.trace("[{}] Extracted payload", ctx.getName());
		ctx.sendUpstream(
				new UpstreamMessageEvent(ctx.getChannel(), rupPacket.getPayload(), ctx.getChannel().getRemoteAddress())
		);
	}
}
