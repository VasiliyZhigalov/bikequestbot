package com.vasiliyzhigalov.bot.botApi;

import com.vasiliyzhigalov.services.menu.CreateQuestStateService;
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
    private final CreateQuestStateService createQuestStateService;

    public BotApiMethod handle(Update update) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        Events currentEvent = getCurrentEvent(update);
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long userId = callbackQuery.getFrom().getId().longValue();
            log.warn("__________________________________");
            log.warn("new update from userId :{}, message: {}, has location: {}", userId, callbackQuery.getData(), false);
            try {
                persister.restore(stateMachine, userId);
                log.warn("State: {}", stateMachine.getState().getId());
                injectServicesToStateMachineContext(stateMachine);
                stateMachine.getExtendedState().getVariables().put("callbackQuery", callbackQuery);
                stateMachine.getExtendedState().getVariables().put("userId", userId);
                log.warn("Send event: {}", currentEvent);
                stateMachine.sendEvent(currentEvent);
                Thread.sleep(1000);
                persister.persist(stateMachine, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            Long userId = message.getFrom().getId().longValue();
            log.warn("__________________________________");
            log.warn("new update from userId :{}, message: {}, has location: {}", userId, message.getText(), message.hasLocation());
            try {
                persister.restore(stateMachine, userId);
                log.warn("State: {}", stateMachine.getState().getId());
                injectServicesToStateMachineContext(stateMachine);
                stateMachine.getExtendedState().getVariables().put("message", message);
                stateMachine.getExtendedState().getVariables().put("userId", userId);
                log.warn("Send event: {}", currentEvent);
                stateMachine.sendEvent(currentEvent);
                Thread.sleep(1000);
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
        stateMachine.getExtendedState().getVariables().put("createQuestStateService", createQuestStateService);
    }

    private Events getCurrentEvent(Update update) {
        Events currentEvent;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            currentEvent = eventsMap.get(callbackQuery.getData()) == null ? Events.DEFAULT : eventsMap.get(callbackQuery.getData());
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasLocation()) {
                currentEvent = Events.SEND_LOCATION_BUTTON;
            } else {
                currentEvent = eventsMap.get(message.getText()) == null ? Events.DEFAULT : eventsMap.get(message.getText());
            }
        } else {
            currentEvent = Events.DEFAULT;
        }
        return currentEvent;
    }

}
