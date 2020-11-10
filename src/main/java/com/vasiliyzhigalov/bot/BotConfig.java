package com.vasiliyzhigalov.bot;

import com.vasiliyzhigalov.bot.botApi.BikeQuestBot;
import com.vasiliyzhigalov.bot.botApi.UpdateProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class BotConfig {
    @Value("${bot.webHookPath}")
    private String webHookPath;
    @Value("${bot.botUserName}")
    private String botUserName;
    @Value("${bot.botToken}")
    private String botToken;
    @Value("${bot.proxyHost}")
    private String proxyHost;
    @Value("${bot.proxyPort}")
    private int proxyPort;


    @Bean
    public BikeQuestBot bikeQuestBot(@Lazy UpdateProcessor updateProcessor) {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        BikeQuestBot bikeQuestBot = new BikeQuestBot(options, updateProcessor);

        bikeQuestBot.setBotToken(botToken);
        bikeQuestBot.setWebHookPath(webHookPath);
        bikeQuestBot.setBotUserName(botUserName);

        return bikeQuestBot;
    }

}
