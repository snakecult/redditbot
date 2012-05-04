package com.rdiablo.bot.reddit.command;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import us.davidandersen.reddit.RedditApi;

import com.rdiablo.bot.reddit.RedditBot;

public class HotListingCommand implements RedditBotCommand {
	@Autowired
	private RedditApi api = new RedditApi();

	@Autowired
	private ListingPrinter printer = new ListingPrinter();

	@Override
	public void execute(RedditBot redditBot, String message) {
		try {
			final String subReddit = RedditBot.getSubReddit(message, redditBot.defaultReddit);
			printer.print(api.getHotListings(subReddit));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
