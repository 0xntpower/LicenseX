package dev.licensex.manager.utils.crypto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class XorUtil {
    private static final String SYMMETRIC_KEY = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";

    public static String decryptXOR(String data) {
        data = xor(data);
        data = toASCII(data);
        data = toBinary(data);
        return data;
    }

    public static String encryptXOR(String data) {
        data = toASCII(data);
        data = xor(data);
        data = toBinary(data);
        return data;
    }

    public static String xor(String s1) {
        s1 = toBinary(s1);
        String s2 = toBinary(SYMMETRIC_KEY);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
            result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
        return result.toString();
    }

    private static String toBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    private static int binaryToDecimal(String n) {
        String num = n;

        // Stores the decimal value
        int dec_value = 0;

        // Initializing base value to 1
        int base = 1;

        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {

            // If the current bit is 1
            if (num.charAt(i) == '1')
                dec_value += base;
            base = base * 2;
        }

        // Return answer
        return dec_value;
    }

    // Function to convert binary to ASCII
    private static String toASCII(String str) {

        // To store size of s
        int N = (str.length());

        // If given String is not a
        // valid String
        if (N % 8 != 0) {
            return "Not Possible!";
        }

        // To store final answer
        String res = "";

        // Loop to iterate through String
        for (int i = 0; i < N; i += 8) {
            int decimal_value
                    = binaryToDecimal((str.substring(i, 8+i)));

            // Apprend the ASCII character
            // equivalent to current value
            res += (char)(decimal_value);
        }

        // Return Answer
        return res;
    }
}
