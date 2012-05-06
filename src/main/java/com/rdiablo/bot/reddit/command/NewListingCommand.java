package com.rdiablo.bot.reddit.command;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import us.davidandersen.reddit.RedditApi;

import com.rdiablo.bot.reddit.RedditBot;

public class NewListingCommand implements RedditBotCommand {
	@Autowired
	private RedditApi api = new RedditApi();

	@Autowired
	private ListingPrinter printer = new ListingPrinter();

	@Override
	public void execute(RedditBot bot, String message) {
		try {
			final String subReddit = RedditBot.getSubReddit(message,
					bot.defaultReddit);
			printer.print(api.getNewListings(subReddit));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
