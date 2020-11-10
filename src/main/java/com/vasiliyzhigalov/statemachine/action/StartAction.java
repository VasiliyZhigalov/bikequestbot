package com.vasiliyzhigalov.statemachine.action;

import com.vasiliyzhigalov.services.menu.SenderService;
import com.vasiliyzhigalov.services.menu.StartStateService;
import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public class StartAction implements Action<States, Events> {
    private StartStateService startStateService;
    private SenderService senderService;
    private Long userId;

    @Override
    public void execute(StateContext<States, Events> stateContext) {
        senderService = stateContext.getExtendedState().get("senderService", SenderService.class);
        startStateService = stateContext.getExtendedState().get("startStateService", StartStateService.class);
        userId = stateContext.getExtendedState().get("userId", Long.class);
        senderService.sendMessage(startStateService.getStartMenu(), userId);
    }
}
