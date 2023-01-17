package dev.licensex.server.api;

public abstract class LXPlugin {
    public abstract void onEnabled();
    public abstract void onDisabled();
}
