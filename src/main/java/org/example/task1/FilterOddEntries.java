package org.example.task1;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterOddEntries {
    public static List<String> filterEntriesAtOddIndicesToList(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    public static String filterEntriesAtOddIndicesToString(List<String> list) {
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.joining(", "));
    }

    public static <T> List<T> genericFilterAtOddIndices(Collection<T> collection) {
        List<T> list;
        if (collection instanceof List) list = (List<T>) collection;
        else list = collection.stream().collect(Collectors.toList());
        return IntStream.range(0, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }
}
