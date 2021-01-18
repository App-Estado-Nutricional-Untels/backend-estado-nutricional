package com.untels.estadonutricional.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {

    public static String snakeUpperToCamelCase(String text) {
        return Stream.of(text.toLowerCase().split("[^a-zA-Z0-9]"))
                .map(v -> v.substring(0, 1).toUpperCase() + v.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }
}
