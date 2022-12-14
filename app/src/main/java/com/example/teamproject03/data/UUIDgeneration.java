package com.example.teamproject03.data;

import java.util.UUID;

public class UUIDgeneration {
    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
