package com.vasiliyzhigalov.statemachine.persist;

import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class InMemoryPersist implements StateMachinePersist<States, Events, Long> {
    private HashMap<Long, StateMachineContext<States, Events>> storage = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> stateMachineContext, Long userId) throws Exception {
        storage.put(userId, stateMachineContext);
    }

    @Override
    public StateMachineContext<States, Events> read(Long userId) throws Exception {
        return storage.get(userId);
    }
}
