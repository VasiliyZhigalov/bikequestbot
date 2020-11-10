package com.vasiliyzhigalov.services.menu;

import com.vasiliyzhigalov.bot.botApi.BikeQuestBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SenderService {
    private final BikeQuestBot bikeQuestBot;

    public SenderService(BikeQuestBot bikeQuestBot) {
        this.bikeQuestBot = bikeQuestBot;
    }

    public void sendMessage(SendMessage sendMessage, Long userId) {
        sendMessage.setChatId(userId.longValue());
        try {
            bikeQuestBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
