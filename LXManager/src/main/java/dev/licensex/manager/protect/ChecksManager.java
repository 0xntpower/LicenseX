package dev.licensex.manager.protect;

import dev.licensex.manager.protect.checks.TaskListCheck;
import dev.licensex.manager.protect.checks.WindowNameCheck;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
