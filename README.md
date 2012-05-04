redditbot
=========

An IRC bot for posting reddit links

# Build from source

    mvn clean package
    
# Configuration

Place a file named bot.properties into the same directory as the jar.

    bot.server=irc.example.com  # server to connect to
    bot.channel=#channel        # channel to join
    bot.commandDelimiter=!      # prefix for commands
    bot.name=redditbot          # bot will change its name to this
    bot.verbose=false           # true for lots of logging

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
