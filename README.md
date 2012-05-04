redditbot
=========

An IRC bot for posting reddit links

# Build from source

    mvn clean package
    
# Configuration

bot.server=irc.example.com
bot.channel=#channel

    
# Running

## Arch Linux

    yaourt redditbot
    nano /etc/redditbot/bot.properties
    /etc/rc.d/redditbot start

## jsvc Daemon

redditbot can be run as a jsvc Daemon.

    jsvc -cp redditbot.jar com.rdiablo.bot.BotDaemon
    
## java

    java -cp redditbot.jar com.rdiablo.bot.BotDaemon
    
# Command Line Options

-Dbot.config /path/to/bot.properties

# Bot Commands

!hot <subreddit> - Get a hot link from reddit
!new <subreddit> - Get a new link from reddit
!rising <subreddit> - Get a rising link from reddit
