package com.rdiablo.bot.reddit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import us.davidandersen.reddit.RedditApi;

import com.rdiablo.bot.reddit.command.HotListingCommand;
import com.rdiablo.bot.reddit.command.ListingPrinter;
import com.rdiablo.bot.reddit.command.NewListingCommand;
import com.rdiablo.bot.reddit.command.RisingListingCommand;
import com.rdiablo.bot.reddit.command.StringCache;

@Configuration
public class BotConfig {

	@Bean
	public RedditBot getBot() {
		return new RedditBot();
	}

	@Bean
	public RedditApi getApi() {
		return new RedditApi();
	}

	@Bean
	public NewListingCommand getNewListingCommand() {
		return new NewListingCommand();
	}

	@Bean
	public RisingListingCommand getRisingListingCommand() {
		return new RisingListingCommand();
	}

	@Bean
	public HotListingCommand getHotListingCommand() {
		return new HotListingCommand();
	}

	@Bean
	public StringCache getStringCache() {
		return new StringCache();
	}

	@Bean
	public ListingPrinter getListingPrinter() {
		return new ListingPrinter();
	}

	@Bean
	public BotProperties getBotProperties() {
		return new BotProperties();
	}
}
