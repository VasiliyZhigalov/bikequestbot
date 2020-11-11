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
    /**
     * Здесь вы можете созадть свой квест
     */
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

    /**
     * Добавить новую точку?
     */
    @Value("${msg.addNewStageQuestion}")
    private String addNewStageQuestion;

    public SendMessage addNewStageQuestionMenu() {
        InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();
        InlineKeyboardMarkup keyboard = inlineKeyboardBuilder
                .addRow()
                .addButton(Events.YES.getInscription(), Events.YES.getInscription()).addButton(Events.NO.getInscription(), Events.NO.getInscription())
                .build();
        return new SendMessage().setText(addNewStageQuestion).setReplyMarkup(keyboard);
    }

    /**
     * Отправьте локацию
     */
    @Value("${msg.sendLocation}")
    private String sendLocation;

    public SendMessage sendLocationMenu() {
        ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
        ReplyKeyboardMarkup keyboard = replyKeyboardBuilder
                .addRow()
                .addButton(Events.SEND_LOCATION_BUTTON.getInscription(), true)
                .build();
        return new SendMessage().setText(sendLocation).setReplyMarkup(keyboard);
    }

    /**
     * Локация сохранена
     */
    @Value("${msg.locationSaved}")
    private String locationSaved;

    public SendMessage locationSavedMenu() {
        return new SendMessage().setText(locationSaved);
    }

    /**
     * Сохранить квест?
     */
    @Value("${msg.saveQuestQuestion}")
    private String saveQuestQuestion;

    public SendMessage saveQuestQuestion() {
        InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();
        InlineKeyboardMarkup keyboard = inlineKeyboardBuilder
                .addRow()
                .addButton(Events.YES.getInscription(), Events.YES.getInscription()).addButton(Events.NO.getInscription(), Events.NO.getInscription())
                .build();
        return new SendMessage().setText(saveQuestQuestion).setReplyMarkup(keyboard);
    }

    /**
     * Квест сохранен
     */
    @Value("${msg.questSaved}")
    private String questSaved;
    public SendMessage questSaved() {
        return new SendMessage().setText(questSaved);
    }
}
