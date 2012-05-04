package com.rdiablo.bot.reddit.command;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import us.davidandersen.reddit.Listing;

import com.rdiablo.bot.reddit.BotProperties;
import com.rdiablo.bot.reddit.RedditBot;

public class ListingPrinter {
	private Logger log = Logger.getLogger(ListingPrinter.class);

	@Autowired
	private StringCache cache = new StringCache();

	@Autowired
	private RedditBot bot;

	@Autowired
	private BotProperties props;

	void print(final List<Listing> listings) {
		for (final Listing listing : listings) {
			if (!cache.contains(listing.permalink)) {
				print(listing);
				return;
			}
		}
		log.debug("no new listings");
	}

	void print(final Listing listing) {
		cache.add(listing.permalink);
		bot.sendMessage(props.getChannel(), "http://www.reddit.com" + listing.permalink);
	}

}
