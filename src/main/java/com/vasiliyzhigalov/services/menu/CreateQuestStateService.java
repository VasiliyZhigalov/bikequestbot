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
public class CreateQuestStateService {

    private ReplyKeyboardMarkup replyKeyboardMarkup() {
        ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
        return replyKeyboardBuilder
                .addRow()
                .addButton(Events.PROCEED_BUTTON.getInscription(), false)
                .addButton(Events.BACK_BUTTON.getInscription(), false)
                .build();
    }
    @Value("${msg.createQuestMessage}")
    private String createQuestMessage;
    public SendMessage getCreateQuestMenu() {
        return new SendMessage().setText(createQuestMessage).setReplyMarkup(replyKeyboardMarkup());
    }

    public SendMessage addLocationMenu() {
    ///Дописать
        return null;git
    }
}
