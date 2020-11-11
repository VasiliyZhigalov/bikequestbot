package com.vasiliyzhigalov.statemachine.listener;

import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class BotStateMachineListener extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        try {
            log.warn("move from: {} to: {}", from.getId(), to.getId());
        } catch (Throwable e) {
        }

    }
}
