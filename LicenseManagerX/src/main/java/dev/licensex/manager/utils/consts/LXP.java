package dev.licensex.manager.utils.consts;

/**
 * The LicenseXProtocol
 */
public final class LXP {

    public static class ACKNOWLEDGEMENTS {
        public static final String CLIENT_REQUEST_PROCESSED_SUCCESSFULLY = "success";
        public static final String CLIENT_REQUEST_PROCESSING_FAILED = "failed";
    }

    public static class DATA_CHECKS {
        public static final String DATA_INSIDE_DATABASE = "positive";
        public static final String DATA_NOT_INSIDE_DATABASE = "negative";
        public static final String NO_DATA_TO_SEND = "empty";
    }

}
