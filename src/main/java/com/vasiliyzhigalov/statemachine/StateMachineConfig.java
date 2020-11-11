package com.vasiliyzhigalov.statemachine;

import com.vasiliyzhigalov.statemachine.action.*;
import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.listener.BotStateMachineListener;
import com.vasiliyzhigalov.statemachine.states.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;

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
        return new BotStateMachineListener();
    }

    @Override
    public void configure(final StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(INIT)
                .state(START, new StartAction())
                .state(CREATE_QUEST, new CreateQuestAction())
                .state(ADD_NEW_STAGE_QUESTION, new AddNewStageQuestionAction())
                .state(ADD_LOCATION, new SendLocationAction())
                .state(LOCATION_SAVED, new LocationSavedAction())
                .state(SAVE_QUEST_QUESTION, new SaveQuestQuestionAction())
                .state(QUEST_SAVED, new QuestSavedAction())
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
                .source(CREATE_QUEST).target(ADD_NEW_STAGE_QUESTION).event(PROCEED_BUTTON)
                .and().withExternal()
                .source(ADD_NEW_STAGE_QUESTION).target(ADD_LOCATION).event(YES)
                .and().withExternal()
                .source(ADD_LOCATION).target(LOCATION_SAVED).event(SEND_LOCATION_BUTTON)
                .and().withExternal()
                .source(LOCATION_SAVED).target(ADD_NEW_STAGE_QUESTION).timer(10)
                .and().withExternal()
                .source(ADD_NEW_STAGE_QUESTION).target(SAVE_QUEST_QUESTION).event(NO)
                .and().withExternal()
                .source(SAVE_QUEST_QUESTION).target(QUEST_SAVED).event(YES)
                .and().withExternal()
                .source(START).target(PASS_QUEST).event(PASS_QUEST_BUTTON);
    }
}
