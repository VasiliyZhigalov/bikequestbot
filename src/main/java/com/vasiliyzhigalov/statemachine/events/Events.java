package com.vasiliyzhigalov.statemachine.events;

public enum Events {
    DEFAULT("По умолчанию"),
    CREATE_QUEST_BUTTON("Создать квест"),
    PASS_QUEST_BUTTON("Пройти квест"),
    BACK_BUTTON("Назад"),
    PROCEED_BUTTON("Продолжить"),
    ADD_LOCATION("Добавить местоположение");

    private String inscription;

    Events(String inscription) {
        this.inscription = inscription;
    }

    public String getInscription() {
        return inscription;
    }

}
