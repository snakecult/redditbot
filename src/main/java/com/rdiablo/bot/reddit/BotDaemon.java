package com.rdiablo.bot.reddit;

import java.io.IOException;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BotDaemon implements Daemon {
	private static Logger log = Logger.getLogger(BotDaemon.class);
	private static RedditBot bot;

	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		log.info("Starting Bot");
		final AnnotationConfigApplicationContext ctx = BotDaemon.getContext();
		final BotProperties props = ctx.getBean(BotProperties.class);
		bot = ctx.getBean(RedditBot.class);
		bot.setVerbose(props.getVerbose());
		bot.connect(props.getServer());
		bot.changeNick(props.getName());
		bot.joinChannel(props.getChannel());
	}

	static AnnotationConfigApplicationContext getContext() {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(BotConfig.class);
		ctx.refresh();
		return ctx;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(DaemonContext arg0) throws DaemonInitException, Exception {
	}

	@Override
	public void start() throws Exception {
		main(new String[] {});
	}

	@Override
	public void stop() throws Exception {
		log.info("Stopping Bot");
		bot.disconnect();
	}

}
