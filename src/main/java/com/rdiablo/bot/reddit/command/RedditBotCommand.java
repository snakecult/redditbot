package com.rdiablo.bot.reddit.command;

import com.rdiablo.bot.reddit.RedditBot;

public interface RedditBotCommand {
	void execute(RedditBot redditBot, String message);
}
