package dev.licensex.server.system.request.requests;

import dev.licensex.server.system.request.RequestExecutor;

public class ExampleRequest2 implements RequestExecutor {
    @Override
    public void onRequest(String... args) {
        String rqArg1 = args[2];
        String rqArg2 = args[3];

        System.out.println("add license to database here");
    }
}
