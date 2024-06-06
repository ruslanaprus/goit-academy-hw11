package org.example.task2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineSorter {
    public static List<String> sortDecreasingOrderToUppercase(List<String> list) {
        return list.stream()
                .map(LineSorter::removeLeadingSpecialCharacters)
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static String removeLeadingSpecialCharacters(String s) {
        return s.replaceFirst("^\\W+", "");
    }
}
