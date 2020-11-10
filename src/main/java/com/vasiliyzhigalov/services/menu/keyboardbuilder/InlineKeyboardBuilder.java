package com.vasiliyzhigalov.services.menu.keyboardbuilder;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardBuilder {

    InlineKeyboardMarkup markupKeyboard;
    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
    private int rowCounter;


    public InlineKeyboardBuilder() {
        this.markupKeyboard = new InlineKeyboardMarkup();
        rowCounter = 0;
    }

    public InlineKeyboardBuilder addRow() {
        List<InlineKeyboardButton> buttonRow = new ArrayList<>();
        buttons.add(buttonRow);
        rowCounter++;
        return this;
    }

    public InlineKeyboardBuilder addButton(String text, String callback) {
        buttons.get(rowCounter - 1).add(new InlineKeyboardButton().setText(text).setCallbackData(callback));
        return this;
    }

    public InlineKeyboardMarkup build() {
        this.markupKeyboard.setKeyboard(buttons);
        return this.markupKeyboard;
    }
}
