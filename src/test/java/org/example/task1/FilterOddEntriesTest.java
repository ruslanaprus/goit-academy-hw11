package org.example.task1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterOddEntriesTest {

    @Test
    void testFilterEntriesAtOddIndicesToList() {
        List<String> list = Arrays.asList("0 - Alice", "1 - Bob", "2 - Charlie", "3 - David", "4 - Eve", "5 - Frank", "6 - Grace");
        List<String> expected = Arrays.asList("1 - Bob", "3 - David", "5 - Frank");

        List<String> listOfEntriesAtOddIndices = FilterOddEntries.filterEntriesAtOddIndicesToList(list);
        System.out.println("listOfEntriesAtOddIndices = " + listOfEntriesAtOddIndices);

        assertEquals(expected, listOfEntriesAtOddIndices);
    }

    @Test
    void testFilterEntriesAtOddIndicesToString() {
        List<String> list = Arrays.asList("0 - Alice", "1 - Bob", "2- Charlie", "3 - David", "4 - Eve", "5 - Frank", "6 - Grace");
        String expected = ("1 - Bob, 3 - David, 5 - Frank");

        String stringOfEntriesAtOddIndices = FilterOddEntries.filterEntriesAtOddIndicesToString(list);
        System.out.println("stringOfEntriesWithOddIndices = " + stringOfEntriesAtOddIndices);

        assertEquals(expected, stringOfEntriesAtOddIndices);
    }
}