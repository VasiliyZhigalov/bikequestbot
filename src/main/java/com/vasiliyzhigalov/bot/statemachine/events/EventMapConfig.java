package com.vasiliyzhigalov.bot.statemachine.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class EventMapConfig {
    @Bean
    public Map<String, Events> eventsMap() {
        List<Events> eventsList = Arrays.asList(Events.values().clone());
        Map<String, Events> eventsMap = new HashMap<>();
        eventsList.forEach(e -> eventsMap.put(e.getInscription(), e));
        return eventsMap;
    }

}
