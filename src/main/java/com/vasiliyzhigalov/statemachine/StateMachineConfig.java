package com.vasiliyzhigalov.statemachine;

import com.vasiliyzhigalov.statemachine.action.CreateQuestAction;
import com.vasiliyzhigalov.statemachine.action.StartAction;
import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

import static com.vasiliyzhigalov.statemachine.states.States.*;
import static com.vasiliyzhigalov.statemachine.events.Events.*;
import static com.vasiliyzhigalov.statemachine.states.States.CREATE_QUEST;
import static com.vasiliyzhigalov.statemachine.states.States.PASS_QUEST;


@Configuration
@EnableStateMachineFactory
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .listener(listener())
                .autoStartup(true);
    }

    private StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void transition(Transition<States, Events> transition) {
    /*            log.warn("move from: {} to: {}",
                        Optional.of(transition.getSource().getId()).get(),
                        Optional.of(transition.getTarget().getId()).orElse(NULL));*/
            }

        };
    }

    @Override
    public void configure(final StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(INIT)
                .state(START, new StartAction())
                .state(CREATE_QUEST, new CreateQuestAction())
                .state(ADD_LOCATION, new CreateQuestAction())
                .end(FINISH);
    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(INIT).target(START).event(DEFAULT)
                .and().withExternal()
                .source(START).target(CREATE_QUEST).event(CREATE_QUEST_BUTTON)
                .and().withExternal()
                .source(CREATE_QUEST).target(START).event(BACK_BUTTON)

                .and().withExternal()
                .source(START).target(PASS_QUEST).event(PASS_QUEST_BUTTON);
    }
}
