package dev.licensex.manager.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LicenseData {
    private String id = "";
    private String username = "";
    private boolean nameInLicense;
    private LICENSE_ID_TYPE idType = LICENSE_ID_TYPE.GROUPS;
    private LIMIT_TYPE limitType = LIMIT_TYPE.PER_MACHINE;
    private int limit = 1;
    private boolean expires;
    private String expiration_date = "";

    public LicenseData() {}
}