package com.rdiablo.bot.reddit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class BotProperties {
	private Logger log = Logger.getLogger(RedditBot.class);
	private ResourceBundle defaultBundle;
	private Properties configProperties;

	public BotProperties() {
		log.info("BotProperties");
		defaultBundle = ResourceBundle.getBundle("bot");
		configProperties = getConfigProperties();
	}

	private Properties getConfigProperties() {
		final Properties properties = new Properties();
		final String botConfig = getBotConfig();
		log.info("Reading config from " + botConfig);
		final File file = new File(botConfig);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

		return properties;
	}

	private String getBotConfig() {
		return getProperty("bot.config");
	}

	public String getServer() {
		return getProperty("bot.server");
	}

	public String getChannel() {
		return getProperty("bot.channel");
	}

	public String getName() {
		return getProperty("bot.name");
	}

	public boolean getVerbose() {
		return Boolean.parseBoolean(getProperty("bot.verbose"));
	}

	public String getCommandDelimiter() {
		return getProperty("bot.commandDelimiter");
	}

	private String getProperty(String key) {
		final String propChain = getPropChain(key);
		log.debug(key + "=" + propChain);
		return propChain;
	}

	private String getPropChain(String key) {
		{
			final String systemProperty = System.getProperty(key);
			if (systemProperty != null) {
				return systemProperty;
			}
		}

		{
			if (configProperties != null) {
				final String confProp = configProperties.getProperty(key);
				if (confProp != null) {
					return confProp;
				}
			}
		}

		return defaultBundle.getString(key);
	}
}
