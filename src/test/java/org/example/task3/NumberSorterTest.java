package org.example.task3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberSorterTest {

    @Test
    void testCollectAndSortNumbers() {
        List<String> testList = Arrays.asList("42, meow, 341, 5, 9, 35, purrrr", "42", "56, y, 67", "meow0", "2");
        List<Integer> expected = List.of(0, 2, 5, 9, 35, 42, 42, 56, 67, 341);

        List<Integer> result = NumberSorter.collectAndSortNumbers(testList);

        System.out.println("expected = " + expected);
        System.out.println("result = " + result);
        assertEquals(expected, result);
        assertTrue(result.contains(341));
        assertTrue(result.contains(0));
    }

    @Test
    void testCollectAndSortNumbers_IfNoNumbers() {
        List<String> testList = Arrays.asList("Whiskers, Buscuit", "Catsby, Purrlock Holmes", "Catpernicus");
        List<Integer> expected = new ArrayList<>();

        List<Integer> result = NumberSorter.collectAndSortNumbers(testList);

        assertEquals(expected, result);
    }

    @Test
    public void testCollectAndSortNumbers_LargeNumbers() {
        List<String> input = Arrays.asList("meow1674574358", "purrr847823967", "bob672634876");
        List<Integer> expected = List.of(672634876, 847823967, 1674574358);

        List<Integer> result = NumberSorter.collectAndSortNumbers(input);

        assertEquals(expected, result);
    }
}