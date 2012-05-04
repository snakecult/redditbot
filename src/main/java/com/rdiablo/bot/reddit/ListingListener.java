package com.rdiablo.bot.reddit;

import us.davidandersen.reddit.Listing;

public interface ListingListener {
	void onNewListing(Listing listing);
}
