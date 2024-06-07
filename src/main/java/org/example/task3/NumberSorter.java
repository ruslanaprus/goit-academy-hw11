package org.example.task3;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberSorter {

    public static List<Integer> collectAndSortNumbers(List<String> list) {
        Pattern pattern = Pattern.compile("\\d+");

        return list.stream()
                .flatMap(str -> pattern.matcher(str).results()
                        .map(matchResult -> Integer.parseInt(matchResult.group())))
                .sorted()
                .collect(Collectors.toList());
    }
}
