package com.rdiablo.bot.reddit.command;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class StringCache {

	private ArrayList<String> cache = new ArrayList<String>();
	private Logger log = Logger.getLogger(StringCache.class);

	public void add(String listing) {
		log.debug("cache=" + listing);
		cache.add(listing);
		if (cache.size() > 100) {
			log.debug("purge");
			cache.remove(0);
		}
	}

	public boolean contains(String permalink) {
		return cache.contains(permalink);
	}

}
