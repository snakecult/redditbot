package com.rdiablo.bot.reddit;

import java.util.Date;

public class TimeUtil {

	static long getCurrentTime() {
		final Date date = new Date();
		return date.getTime() / 1000;
	}

	public static long getElapsedTime(long startTime) {
		return getCurrentTime() - startTime;
	}

	public static String formatTime(final long time) {
		if (time < 60)
			return time + "s";
		if (time < 3600)
			return time / 60 + "m";
		return time / 3600 + "h";
	}
}
