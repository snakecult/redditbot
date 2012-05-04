redditbot
=========

An IRC bot for posting reddit links

= Building

    mvn clean package
    
= Running

== jsvc

    jsvc -cp redditbot.jar com.rdiablo.bot.BotDaemon
    
== java

    java -cp redditbot.jar com.rdiablo.bot.BotDaemon