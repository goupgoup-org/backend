package com.example.admin.common.utils;

import java.util.UUID;

public class UidUtil {

    public static String generateUid() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
