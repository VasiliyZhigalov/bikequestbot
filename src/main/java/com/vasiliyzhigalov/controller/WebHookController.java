package com.vasiliyzhigalov.controller;

import com.vasiliyzhigalov.bot.botApi.BikeQuestBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private BikeQuestBot bikeQuestBot;

    @Autowired
    public WebHookController(BikeQuestBot bikeQuestBot) {
        this.bikeQuestBot = bikeQuestBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod onWebHookUpdateReceived(@RequestBody Update update) {
        return bikeQuestBot.onWebhookUpdateReceived(update);
    }
}
