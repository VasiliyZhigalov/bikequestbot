package com.vasiliyzhigalov.bot.statemachine.events;

import org.springframework.beans.factory.annotation.Value;

public enum Events {
    DEFAULT("По умолчанию");

    private String inscription;

    Events(String inscription) {
        this.inscription = inscription;
    }

    public String getInscription() {
        return inscription;
    }

}
