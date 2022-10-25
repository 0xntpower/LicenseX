package dev.licensex.manager.winapi;

import dev.licensex.manager.winapi.checks.WindowNameCheck;

import java.util.LinkedList;
import java.util.List;

public class ChecksManager {
    private final List<Check> checks = new LinkedList<>();

    public ChecksManager() {
        checks.add(new WindowNameCheck());
    }

    public void runSystemChecks() {
        for (Check check : checks)
            check.check();
    }
}
