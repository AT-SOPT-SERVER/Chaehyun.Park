package org.sopt.global.util;

public class IdGenerator {
    private static int currentId = 1;

    public static synchronized int generateId() {
        return currentId++;
    }
}
