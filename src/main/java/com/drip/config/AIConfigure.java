package com.drip.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfigure {

    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }
}
