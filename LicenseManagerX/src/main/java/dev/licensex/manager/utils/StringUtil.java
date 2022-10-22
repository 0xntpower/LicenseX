package dev.licensex.manager.utils;

import java.util.ArrayList;
import java.util.List;
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

    public static boolean isStringNumber(String str) {
        for (char c : str.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    public static String[] split(String input, char separator) {
        ArrayList<String> args = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {

            if (c == separator) {
                args.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }

        }

        args.add(sb.toString());

        String[] arr = new String[args.size()];
        args.toArray(arr);

        return arr;
    }

    public static String splitCategoryName(String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {

            if (c != '{') {
                sb.append(c);
            } else
                break;

        }

        return sb.toString();
    }

    public static ArrayList<String> splitProductsName(String input) {
        ArrayList<String> products = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        boolean insideBrakets = false;

        for (char c : input.toCharArray()) {

            if (c == '=') {
                products.add(sb.toString());
                sb = new StringBuilder();
                insideBrakets = false;
            } else {
                if (c != '{' && c != '|') {
                    if (insideBrakets)
                        sb.append(c);
                } else {
                    insideBrakets = true;
                }
            }

        }

        return products;
    }

    public static ArrayList<String> splitProductsLine(String input) {
        ArrayList<String> products = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        boolean insideBrakets = false;

        for (char c : input.toCharArray()) {

            if (c == '}') {
                products.add(sb.toString());
                sb = new StringBuilder();
                insideBrakets = false;
            } else {
                if (c != '{' && c != '|') {
                    if (insideBrakets)
                        sb.append(c);
                } else {
                    insideBrakets = true;
                }
            }

        }

        return products;
    }

    public static List<String> splitProductLicenses(String input) {
        List<String> licenses = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        boolean insideBrakets = false;

        for (char c : input.toCharArray()) {

            if (c == ',') {
                licenses.add(sb.toString());
                sb = new StringBuilder();
            }

            if (c == '[') {
                insideBrakets = true;
            } else {
                if (insideBrakets && c != ' ' && c != ',') {
                    sb.append(c);
                }
            }

        }

        return licenses;
    }
}
