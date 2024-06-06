package org.example.task1;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OddIndexEntries {
    public static List<String> collectEntriesToList(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    public static String collectEntriesToString(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.joining(", "));
    }

    public static <T> List<T> collectGenericEntries(Collection<T> collection) {
        List<T> list;
        if (collection instanceof List) list = (List<T>) collection;
        else list = collection.stream().collect(Collectors.toList());
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    public static Map<Integer, String> collectEntriesFromMap(Map<Integer, String> map) {
        if (map == null) {
            return Collections.emptyMap();
        }
        return map.entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getKey() % 2 != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
