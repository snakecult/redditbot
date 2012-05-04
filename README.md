redditbot
=========

An IRC bot for posting reddit links

# Build from source

    mvn clean package
    
# Running

## with jsvc

    jsvc -cp redditbot.jar com.rdiablo.bot.BotDaemon
    
## with java

    java -cp redditbot.jar com.rdiablo.bot.BotDaemon