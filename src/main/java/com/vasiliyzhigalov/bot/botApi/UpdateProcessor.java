package com.vasiliyzhigalov.bot.botApi;

import com.vasiliyzhigalov.services.menu.SenderService;
import com.vasiliyzhigalov.services.menu.StartStateService;
import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class UpdateProcessor {

    private final StateMachineFactory<States, Events> stateMachineFactory;
    private final Map<String, Events> eventsMap;
    private final StateMachinePersister<States, Events, Long> persister;
    private final StartStateService startStateService;
    private final SenderService senderService;

    public BotApiMethod handle(Update update) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        log.warn("new update from userId :{}, message: {}", update.getMessage().getChatId(), update.getMessage().getText());
        injectServicesToStateMachineContext(stateMachine);
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            stateMachine.getExtendedState().getVariables().put("callbackQuery", callbackQuery);
            Long userId = callbackQuery.getFrom().getId().longValue();
            stateMachine.getExtendedState().getVariables().put("userId", userId);

            try {
                persister.restore(stateMachine, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stateMachine.sendEvent(getCurrentEvent(callbackQuery.getData()));
            try {
                persister.persist(stateMachine, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            stateMachine.getExtendedState().getVariables().put("message", message);
            Long userId = message.getFrom().getId().longValue();
            stateMachine.getExtendedState().getVariables().put("userId", userId);
            try {
                persister.restore(stateMachine, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stateMachine.sendEvent(getCurrentEvent(message.getText()));
            try {
                persister.persist(stateMachine, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private void injectServicesToStateMachineContext(StateMachine<States, Events> stateMachine) {
        stateMachine.getExtendedState().getVariables().put("startStateService", startStateService);
        stateMachine.getExtendedState().getVariables().put("senderService", senderService);
    }

    private Events getCurrentEvent(String messageText) {

        Events currentEvent = eventsMap.get(messageText) == null ? Events.DEFAULT : eventsMap.get(messageText);
        log.warn("send event : {}", currentEvent);
        return currentEvent;
    }

}
