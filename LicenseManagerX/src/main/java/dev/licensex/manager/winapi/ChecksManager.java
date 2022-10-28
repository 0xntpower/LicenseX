package dev.licensex.manager.winapi;

import dev.licensex.manager.winapi.checks.TaskListCheck;
import dev.licensex.manager.winapi.checks.WindowNameCheck;

import java.util.*;

public class ChecksManager {
    private final List<Check> checks = new LinkedList<>();

    public ChecksManager() {
        checks.add(new WindowNameCheck());
        checks.add(new TaskListCheck());
    }

    public void runSystemChecks() {
        for (Check check : checks)
            check.check();
    }

    public void registerScheduler(long seconds) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Checking for sniffers");
                runSystemChecks();
            }
        };
        Timer timer = new Timer("Timer");

        long delayMS = seconds * 1000;
        timer.schedule(task, delayMS);
    }
}
