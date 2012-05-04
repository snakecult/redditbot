package com.rdiablo.bot.reddit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;

import us.davidandersen.reddit.Listing;

import com.rdiablo.bot.reddit.command.HotListingCommand;
import com.rdiablo.bot.reddit.command.NewListingCommand;
import com.rdiablo.bot.reddit.command.RisingListingCommand;

public class RedditBot extends PircBot implements ListingListener {
	private static final int QUIET_PERIOD = 120;
	private Logger log = Logger.getLogger(RedditBot.class);
	private final List<String> messages = new ArrayList<String>();
	private long lastMessageTime;

	@Autowired
	private NewListingCommand newListingCommand;

	@Autowired
	private RisingListingCommand risingListingCommand;

	@Autowired
	private HotListingCommand hotListingCommand;

	@Autowired
	private BotProperties botProperties;

	public String defaultReddit = "diablo";

	public RedditBot() {
		resetLastMessageTime();
		setName("redditbot");
	}

	@Override
	public synchronized void onMessage(String channel, String sender, String login, String hostname, String message) {
		messages.add(message);
		while (messages.size() > 100) {
			messages.remove(0);
		}
		resetLastMessageTime();

		if (message.startsWith(botProperties.getCommandDelimiter())) {
			final String args[] = message.split(" ");
			final String cmd = args[0];
			if (cmd.equals("?new")) {
				newListingCommand.execute(this, message);
			}
			if (cmd.equals("?rising")) {
				risingListingCommand.execute(this, message);
			}
			if (cmd.equals("?hot")) {
				hotListingCommand.execute(this, message);
			}
		}
	}

	private void resetLastMessageTime() {
		lastMessageTime = TimeUtil.getCurrentTime();
	}

	public synchronized void onNewListing(final Listing listing) {
		if (isOkToPost(listing)) {
			sendMessage(botProperties.getChannel(), listing.author + " just posted \"" + listing.title + "\"" + " to /r/diablo");
			sendMessage(botProperties.getChannel(), "http://www.reddit.com" + listing.permalink);
		}
	}

	private boolean isOkToPost(final Listing listing) {
		if (isAlreadyPosted(listing)) {
			log.info("Someone already posted " + listing.title);
			return false;
		}

		if (isSomeoneTalking()) {
			log.info("Someone is talking");
			return false;
		}

		return false;
		// return true;
	}

	private boolean isSomeoneTalking() {
		final long secondsSinceLastMessage = TimeUtil.getCurrentTime() - lastMessageTime;
		if (secondsSinceLastMessage < QUIET_PERIOD)
			return true;

		return false;
	}

	private boolean isAlreadyPosted(Listing listing) {
		return messages.contains(listing.title) || messages.contains(listing.permalink);
	}

	public static String getSubReddit(String message, String defaultReddit) {
		final String[] args = message.split(" ");
		if (args.length >= 2) {
			return args[1];
		}

		return defaultReddit;
	}
}
