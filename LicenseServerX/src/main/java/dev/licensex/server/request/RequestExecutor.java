package dev.licensex.server.request;

public interface RequestExecutor {
    void onRequest(String... args);
}
