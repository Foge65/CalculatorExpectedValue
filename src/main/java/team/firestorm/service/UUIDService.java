package team.firestorm.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UUIDService {
    private final Map<String, String> UUIDs = new HashMap<>();

    public void addUUID(String sessionId, String uuid) {
        UUIDs.put(sessionId, uuid);
    }

    public String getUUID(String sessionId) {
        return UUIDs.get(sessionId);
    }

    public boolean isValidUUID(String uuid) {
        return uuid != null;
    }
}
