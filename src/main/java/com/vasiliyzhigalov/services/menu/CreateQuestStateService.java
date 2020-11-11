package com.vasiliyzhigalov.services.menu;


import com.vasiliyzhigalov.services.menu.keyboardbuilder.InlineKeyboardBuilder;
import com.vasiliyzhigalov.services.menu.keyboardbuilder.ReplyKeyboardBuilder;
import com.vasiliyzhigalov.statemachine.events.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service
@Slf4j
public class CreateQuestStateService {

    @Value("${msg.createQuestMessage}")
    private String createQuestMessage;

    public SendMessage getCreateQuestMenu() {
        ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
        ReplyKeyboardMarkup keyboard = replyKeyboardBuilder
                .addRow()
                .addButton(Events.PROCEED_BUTTON.getInscription(), false)
                .addButton(Events.BACK_BUTTON.getInscription(), false)
                .build();
        return new SendMessage().setText(createQuestMessage).setReplyMarkup(keyboard);
    }

    public SendMessage addLocationMenu() {
        ///Дописать
        return null;
    }

    @Value("${msg.addNewStageQuestion}")
    private String addNewStageQuestion;

    public SendMessage addNewStageQuestionMenu() {
        InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();
        InlineKeyboardMarkup keyboard = inlineKeyboardBuilder
                .addRow()
                .addButton(Events.YES.getInscription(), "Yes").addButton(Events.NO.getInscription(), "No")
                .build();
        return new SendMessage().setText(addNewStageQuestion).setReplyMarkup(keyboard);
    }
}
