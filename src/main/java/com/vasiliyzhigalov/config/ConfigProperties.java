package com.vasiliyzhigalov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "replyMessages.properties",encoding="UTF-8")
public class ConfigProperties {

}
