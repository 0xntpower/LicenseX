package dev.licensex.server.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class StringUtil {
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
}
