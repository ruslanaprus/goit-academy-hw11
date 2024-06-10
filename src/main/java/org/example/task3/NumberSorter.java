/**
 * Provides utility methods for collecting and sorting numbers from strings.
 */
package org.example.task3;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class for collecting and sorting numbers from strings.
 */
public class NumberSorter {

    /**
     * Collects and sorts numbers from a list of strings.
     *
     * <p>Numbers are extracted using regular expressions, parsed as integers, and then sorted in ascending order.</p>
     *
     * @param list The list of strings containing numbers.
     * @return A sorted list of integers extracted from the input strings.
     * @throws NullPointerException if the list or any of its elements are null.
     * @throws NumberFormatException if any extracted number cannot be parsed as an integer.
     */
    public static List<Integer> collectAndSortNumbers(List<String> list) {
        Pattern pattern = Pattern.compile("\\d+");
        return list.stream()
                .flatMap(str -> pattern.matcher(str).results()
                        .map(matchResult -> Integer.parseInt(matchResult.group())))
                .sorted()
                .collect(Collectors.toList());
    }
}
