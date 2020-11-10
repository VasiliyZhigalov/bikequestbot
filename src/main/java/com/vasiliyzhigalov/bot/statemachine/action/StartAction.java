package com.vasiliyzhigalov.bot.statemachine.action;

import com.vasiliyzhigalov.bot.statemachine.events.Events;
import com.vasiliyzhigalov.bot.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class StartAction  implements Action<States, Events> {
    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
}
