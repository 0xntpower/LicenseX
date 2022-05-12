package dev.licensex.server.system.request;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestsManager {
    private final Map<String, RequestExecutor> requests = new HashMap<>();

    public void registerRequestExecutor(String name, RequestExecutor executor) {
        requests.put(name, executor);
    }

    public boolean doesRequestExist(String name) {
        return requests.containsKey(name);
    }

    public RequestExecutor getRequestExecutor(String name) {
        return requests.get(name);
    }
}
