package aden.dev.site.util;

public class StringUtils {
    public static String lowercaseFirstChar(String input) {
        return Character.toLowerCase(input.charAt(0)) + input.substring(1);
    }
}
