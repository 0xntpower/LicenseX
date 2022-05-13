package dev.licensex.server.utils;

import lombok.Getter;

@Getter
public enum LicensingMode {
    FLOATING("Floating"), PER_MACHINE("Per_machine");

    String name;

    LicensingMode(String name) {
        this.name = name;
    }
}
