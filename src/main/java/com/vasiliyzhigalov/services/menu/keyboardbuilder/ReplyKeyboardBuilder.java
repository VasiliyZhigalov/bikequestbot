package com.vasiliyzhigalov.services.menu.keyboardbuilder;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardBuilder {
    private ReplyKeyboardMarkup keyBoardMarkup = new ReplyKeyboardMarkup();
    private List<KeyboardRow> keyboard;
    private int rowCounter;

    public ReplyKeyboardBuilder() {
        keyBoardMarkup.setSelective(true);
        keyBoardMarkup.setResizeKeyboard(true);
        keyBoardMarkup.setOneTimeKeyboard(false);
        keyboard = new ArrayList<>();
        rowCounter = 0;
    }
    public ReplyKeyboardBuilder setOneTimeKeyboard(Boolean oneTimeKeyboard) {
        keyBoardMarkup.setOneTimeKeyboard(oneTimeKeyboard);
        return this;
    }

    public ReplyKeyboardBuilder addRow() {
        KeyboardRow keyboardrow = new KeyboardRow();
        keyboard.add(keyboardrow);
        rowCounter++;
        return this;
    }

    public ReplyKeyboardBuilder addButton(String text, Boolean setLocation) {
        KeyboardButton keyboardButton = new KeyboardButton(text);
        keyboardButton.setRequestLocation(setLocation);
        keyboard.get(rowCounter - 1).add(keyboardButton);
        return this;
    }

    public ReplyKeyboardMarkup build() {
        keyBoardMarkup.setKeyboard(keyboard);
        return keyBoardMarkup;
    }


}
