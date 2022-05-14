package dev.licensex.server.request.requests;

import dev.licensex.server.request.RequestExecutor;

public class ExampleRequest1 implements RequestExecutor {
    @Override
    public void onRequest(String... args) {
        String rqArg1 = args[0];
        String rqArg2 = args[1];

        System.out.println("check if license is in database here");
    }
}
