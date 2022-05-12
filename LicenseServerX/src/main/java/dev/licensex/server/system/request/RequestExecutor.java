package dev.licensex.server.system.request;

public interface RequestExecutor {
    void onRequest(String... args);
}
