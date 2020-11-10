package com.vasiliyzhigalov.bot.statemachine;

import com.vasiliyzhigalov.bot.statemachine.action.StartAction;
import com.vasiliyzhigalov.bot.statemachine.events.Events;
import com.vasiliyzhigalov.bot.statemachine.states.States;
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

import static com.vasiliyzhigalov.bot.statemachine.states.States.*;
import static com.vasiliyzhigalov.bot.statemachine.events.Events.*;


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
                log.warn("move from:{} to:{}",
                        Optional.ofNullable(transition.getSource().getId()),
                        Optional.ofNullable(transition.getTarget().getId()));
            }
        };
    }

    @Override
    public void configure(final StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(INIT)
                .state(START, new StartAction())
                .end(FINISH);
    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(INIT)
                .target(START)
                .event(DEFAULT);
    }
}
