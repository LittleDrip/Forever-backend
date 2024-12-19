package com.drip.util;

import cn.hutool.core.lang.UUID;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatSessionManager {
    private final Map<String, UUID> userSessionMap = new ConcurrentHashMap<>();

    public UUID getOrCreateSession(String userId) {
        return userSessionMap.computeIfAbsent(userId, key -> UUID.fastUUID());
    }

    public UUID getSession(String userId) {
        return userSessionMap.get(userId);
    }

    public void removeSession(String userId) {
        userSessionMap.remove(userId);
    }
}
