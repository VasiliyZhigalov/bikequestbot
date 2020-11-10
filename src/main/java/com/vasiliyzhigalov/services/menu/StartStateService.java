package com.vasiliyzhigalov.services.menu;

import com.vasiliyzhigalov.services.menu.keyboardbuilder.ReplyKeyboardBuilder;
import com.vasiliyzhigalov.statemachine.events.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service
@Slf4j
public class StartStateService {

    private ReplyKeyboardMarkup replyKeyboardMarkup() {
        ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
        return replyKeyboardBuilder
                .addRow()
                .addButton(Events.CREATE_QUEST_BUTTON.getInscription(), false)
                .addButton(Events.PASS_QUEST_BUTTON.getInscription(), false)
                .build();
    }

    @Value("${msg.welcomeMessage}")
    private String welcomeMessage;

    public SendMessage getStartMenu() {
        return new SendMessage().setText(welcomeMessage).setReplyMarkup(replyKeyboardMarkup());
    }
}
