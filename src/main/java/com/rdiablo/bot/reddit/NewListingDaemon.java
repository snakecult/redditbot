package com.rdiablo.bot.reddit;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;

import us.davidandersen.reddit.Listing;
import us.davidandersen.reddit.RedditApi;

public class NewListingDaemon extends Thread {
	private static final int POLL_TIME = 1000 * 60;
	private Logger log = Logger.getLogger(NewListingDaemon.class);
	private ListingListener listener;
	private long startTime;

	@Override
	public void run() {
		try {
			resetTime();
			log.info("startTime = " + startTime);
			while (true) {
				process();
				Thread.sleep(POLL_TIME);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void resetTime() {
		setStartTime(TimeUtil.getCurrentTime());
	}

	private void setStartTime(final long time) {
		startTime = time;
	}

	private void process() throws IOException, JSONException {
		final RedditApi api = new RedditApi();
		List<Listing> listings = api.getNewListings("diablo");
		Listing newest = listings.get(0);
		long a2 = TimeUtil.getCurrentTime() - newest.created_utc;
		log.info("age=" + TimeUtil.formatTime(a2) + " title=\"" + newest.title + "\"");
		for (Listing listing : listings) {
			long age = startTime - listing.created_utc;
			if (listing.created_utc > startTime) {
				log.info("Got new listing");
				log.info("title=" + listing.title);
				log.info("permalink=" + listing.permalink);
				log.info("created_utc=" + listing.created_utc);
				log.info("age=" + TimeUtil.formatTime(age));
				listener.onNewListing(listing);
				setStartTime(listing.created_utc + 1);
			}
		}
	}

	public void addListener(ListingListener bot2) {
		this.listener = bot2;
		
	}
}
