package dev.licensex.manager.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class StringUtil {

    private static final char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

    public static String generateString(int length) {
        return IntStream.range(0, length)
                .mapToObj(i -> Character.toString(chars[ThreadLocalRandom.current().nextInt(chars.length)]))
                .collect(Collectors.joining());
    }

    public static String makeUnreadable(String string) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (char c : string.toCharArray()) {
            stringBuilder.append((char) (c + '\u7159'));
        }

        return stringBuilder.toString();
    }

    public static int getLastIndexOf(String str, char c) {
        int index = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == c)
                index = i;
        return index;
    }
}
