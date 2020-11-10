package com.vasiliyzhigalov.statemachine.persist;

import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {
    @Bean
    public StateMachinePersist<States, Events, Long> inMemoryPersist() {
        return new InMemoryPersist();
    }

    public StateMachinePersister<States, Events, Long> persister(StateMachinePersist<States, Events, Long> defaultPersist) {
        return new DefaultStateMachinePersister<>(defaultPersist);
    }
}
