/**
 * Provides utility methods for collecting entries from collections, lists, and maps based on their odd indices or keys.
 */
package org.example.task1;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for collecting entries from various data structures based on their index or key.
 */
public class OddIndexEntries {

    /**
     * Collects entries from a collection based on their odd indices.
     *
     * <p>If the provided collection is an instance of {@code List}, it is directly used.
     * Otherwise, the collection is converted to a {@code List}.</p>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection from which to collect entries.
     * @return A list of entries from the collection with odd indices.
     * @throws NullPointerException if the collection or any of its elements are null.
     */
    public static <T> List<T> collectGenericEntries(Collection<T> collection) {
        List<T> list;
        if (collection instanceof List) {
            list = (List<T>) collection;
        } else {
            list = collection.stream().collect(Collectors.toList());
        }
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    /**
     * Collects entries from a list of strings based on their odd indices and joins them into a single string.
     *
     * <p>The entries are concatenated with ", " as the delimiter.</p>
     *
     * @param list The list of strings from which to collect entries.
     * @return A string of entries from the list with odd indices, joined by ", ".
     * @throws NullPointerException if the list or any of its elements are null.
     */
    public static String collectEntriesToString(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.joining(", "));
    }

    /**
     * Collects entries from a map based on their odd keys.
     *
     * <p>If the map is {@code null}, an empty map is returned. Only entries with non-null keys that are odd are collected.</p>
     *
     * @param map The map from which to collect entries.
     * @return A map of entries with odd keys.
     */
    public static Map<Integer, String> collectEntriesFromMap(Map<Integer, String> map) {
        if (map == null) {
            return Collections.emptyMap();
        }
        return map.entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getKey() % 2 != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
