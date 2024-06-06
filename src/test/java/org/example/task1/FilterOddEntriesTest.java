package org.example.task1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    void testFilterEntriesAtOddIndicesToListNoEntries() {
        List<String> list = List.of("0 - Alice");

        List<String> listOfEntriesAtOddIndices = FilterOddEntries.filterEntriesAtOddIndicesToList(list);
        System.out.println("listOfEntriesAtOddIndices = " + listOfEntriesAtOddIndices);

        assertEquals(Collections.emptyList(), listOfEntriesAtOddIndices);
        assertTrue(listOfEntriesAtOddIndices.size() == 0);
    }

    @Test
    void testFilterEntriesAtOddIndicesToString() {
        List<String> list = Arrays.asList("0 - Alice", "1 - Bob", "2- Charlie", "3 - David", "4 - Eve", "5 - Frank", "6 - Grace");
        String expected = ("1 - Bob, 3 - David, 5 - Frank");

        String stringOfEntriesAtOddIndices = FilterOddEntries.filterEntriesAtOddIndicesToString(list);
        System.out.println("stringOfEntriesAtOddIndices = " + stringOfEntriesAtOddIndices);

        assertEquals(expected, stringOfEntriesAtOddIndices);
    }

    @Test
    void testFilterEntriesAtOddIndicesToStringNoEntries() {
        List<String> list = Arrays.asList("0 - Alice");
        String expected = ("");

        String stringOfEntriesAtOddIndices = FilterOddEntries.filterEntriesAtOddIndicesToString(list);

        assertEquals(expected, stringOfEntriesAtOddIndices);
        assertTrue(stringOfEntriesAtOddIndices.length() == 0);
    }

    @Test
    void testGenericFilterAtOddIndices(){
        User alice = new User(0, "Alice");
        User bob = new User(1, "Bob");
        User charlie = new User(2, "Charlie");
        User david = new User(3, "David");
        User eve = new User(4, "Eve");
        User frank = new User(5, "Frank");

        List<User> userList = Arrays.asList(alice, bob, charlie, david, eve, frank);
        List<User> expected = Arrays.asList(bob, david, frank);

        List<User> result = FilterOddEntries.genericFilterAtOddIndices(userList);

        System.out.println("userList = " + userList);
        System.out.println("result = " + result);
        System.out.println("expected = " + expected);

        assertFalse(result.contains(alice));
        assertTrue(result.contains(bob));
        assertEquals(expected, result);
    }

    @Test
    void testReturnsEmptyIfOne() {
        User meow = new User(0, "meow");

        List<User> userList = Arrays.asList(meow);

        List<User> result = FilterOddEntries.genericFilterAtOddIndices(userList);

        List<User> empty = new ArrayList<>();

        assertEquals(empty, result);
        assertTrue(userList.size() == 1);
        assertTrue(result.size() == 0);
        assertEquals(0, result.size());
    }
}