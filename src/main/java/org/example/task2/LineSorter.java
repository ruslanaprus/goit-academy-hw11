/**
 * Provides utility methods for sorting lines in decreasing order and converting them to uppercase.
 */
package org.example.task2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for sorting lines in decreasing order and converting them to uppercase.
 */
public class LineSorter {

    /**
     * Sorts lines in decreasing order of their natural ordering (case-insensitive) and converts them to uppercase.
     *
     * <p>Leading special characters are removed before sorting and conversion.</p>
     *
     * @param list The list of strings to be sorted and converted.
     * @return A sorted list of strings in uppercase.
     * @throws NullPointerException if the list or any of its elements are null.
     */
    public static List<String> sortDecreasingOrderToUppercase(List<String> list) {
        return list.stream()
                .map(LineSorter::removeLeadingSpecialCharacters)
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * Removes leading special characters from the input string.
     *
     * @param s The input string.
     * @return The string with leading special characters removed.
     * @throws NullPointerException if the input string is null.
     */
    private static String removeLeadingSpecialCharacters(String s) {
        return s.replaceFirst("^\\W+", "");
    }
}
