package com.example.client.common.utils;

import java.util.UUID;

public class UidUtil {

    public static String generateUid() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
