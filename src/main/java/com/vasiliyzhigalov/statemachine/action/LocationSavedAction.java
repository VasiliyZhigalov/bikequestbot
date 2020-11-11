package com.vasiliyzhigalov.statemachine.action;

import com.vasiliyzhigalov.services.menu.CreateQuestStateService;
import com.vasiliyzhigalov.services.menu.SenderService;
import com.vasiliyzhigalov.statemachine.events.Events;
import com.vasiliyzhigalov.statemachine.states.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class LocationSavedAction implements Action<States, Events> {
    private CreateQuestStateService createQuestStateService;
    private SenderService senderService;
    private Long userId;

    @Override
    public void execute(StateContext<States, Events> stateContext) {
        senderService = stateContext.getExtendedState().get("senderService", SenderService.class);
        createQuestStateService = stateContext.getExtendedState().get("createQuestStateService", CreateQuestStateService.class);
        userId = stateContext.getExtendedState().get("userId", Long.class);
        senderService.sendMessage(createQuestStateService.locationSavedMenu(), userId);
    }
}
