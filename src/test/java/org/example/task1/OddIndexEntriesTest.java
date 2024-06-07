package org.example.task1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OddIndexEntriesTest {

    @Test
    void testCollectGenericEntries() {
        List<String> list = Arrays.asList("0 - Alice", "1 - Bob", "2 - Charlie", "3 - David", "4 - Eve", "5 - Frank", "6 - Grace");
        List<String> expected = Arrays.asList("1 - Bob", "3 - David", "5 - Frank");

        List<String> listOfEntriesAtOddIndices = OddIndexEntries.collectGenericEntries(list);
        System.out.println("listOfEntriesAtOddIndices = " + listOfEntriesAtOddIndices);
        System.out.println("expected = " + expected);

        assertEquals(expected, listOfEntriesAtOddIndices);
    }

    @Test
    void testCollectGenericEntries_ReturnsEmptyIfOne() {
        List<String> list = List.of("0 - Alice");

        List<String> emptyList = OddIndexEntries.collectGenericEntries(list);
        System.out.println("emptyList = " + emptyList);

        assertEquals(Collections.emptyList(), emptyList);
        assertTrue(emptyList.size() == 0);
    }

    @Test
    void testCollectGenericEntries_WithObjectsOfUserClass() {
        User alice = new User(0, "Alice");
        User bob = new User(1, "Bob");
        User charlie = new User(2, "Charlie");
        User david = new User(3, "David");
        User eve = new User(4, "Eve");
        User frank = new User(5, "Frank");

        List<User> userList = Arrays.asList(alice, bob, charlie, david, eve, frank);
        List<User> expected = Arrays.asList(bob, david, frank);

        List<User> result = OddIndexEntries.collectGenericEntries(userList);

        System.out.println("userList = " + userList);
        System.out.println("result = " + result);
        System.out.println("expected = " + expected);

        assertFalse(result.contains(alice));
        assertTrue(result.contains(bob));
        assertEquals(expected, result);
    }

    @Test
    void testCollectGenericEntries_ReturnsEmptyIfOneUser() {
        User bob = new User(0, "Bob");

        List<User> userList = Arrays.asList(bob);

        List<User> result = OddIndexEntries.collectGenericEntries(userList);

        List<User> empty = new ArrayList<>();

        assertEquals(empty, result);
        assertTrue(userList.size() == 1);
        assertTrue(result.size() == 0);
        assertEquals(0, result.size());
    }

    @Test
    void testCollectEntriesToString() {
        List<String> list = Arrays.asList("0 - Alice", "1 - Bob", "2- Charlie", "3 - David", "4 - Eve", "5 - Frank", "6 - Grace");
        String expected = ("1 - Bob, 3 - David, 5 - Frank");

        String stringOfEntriesAtOddIndices = OddIndexEntries.collectEntriesToString(list);
        System.out.println("stringOfEntriesAtOddIndices = " + stringOfEntriesAtOddIndices);
        System.out.println("expected = " + expected);

        assertEquals(expected, stringOfEntriesAtOddIndices);
    }

    @Test
    void testCollectEntriesToString_ReturnsEmptyIfOne() {
        List<String> list = Arrays.asList("0 - Alice");
        String expected = ("");

        String stringOfEntriesAtOddIndices = OddIndexEntries.collectEntriesToString(list);

        assertEquals(expected, stringOfEntriesAtOddIndices);
        assertTrue(stringOfEntriesAtOddIndices.length() == 0);
    }

    @Test
    void testCollectEntriesFromMap() {
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(0, "Alice");
        inputMap.put(1, "Bob");
        inputMap.put(2, "Charlie");
        inputMap.put(3, "David");
        inputMap.put(4, "Eve");
        inputMap.put(5, "Frank");

        Collection<String> expected = List.of("Bob", "David", "Frank");

        Map<Integer, String> result = OddIndexEntries.collectEntriesFromMap(inputMap);

        System.out.println("result = " + result.values());
        System.out.println("expected = " + expected);

        assertEquals(false, result.containsValue("Alice"));
        assertEquals(true, result.containsValue("Bob"));
        assertEquals(false, result.containsValue("Charlie"));
        assertEquals(true, result.containsValue("David"));
        assertEquals(false, result.containsValue("Eve"));
        assertEquals(true, result.containsValue("Frank"));
    }

    @Test
    public void testCollectEntriesFromMap_withOnlyOddKeys() {
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "Alice");
        inputMap.put(3, "Bob");
        inputMap.put(5, "Eve");

        Map<Integer, String> expected = new HashMap<>(inputMap);

        Map<Integer, String> result = OddIndexEntries.collectEntriesFromMap(inputMap);

        assertEquals(expected, result);
    }

    @Test
    public void testCollectEntriesFromMap_withOnlyEvenKeys() {
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(0, "Alice");
        inputMap.put(2, "Bob");
        inputMap.put(4, "Eve");

        Map<Integer, String> expected = Collections.emptyMap();

        Map<Integer, String> result = OddIndexEntries.collectEntriesFromMap(inputMap);

        assertEquals(expected, result);
    }

    @Test
    public void testCollectEntriesFromMap_withEmptyMap() {
        Map<Integer, String> inputMap = new HashMap<>();

        Map<Integer, String> expected = Collections.emptyMap();

        Map<Integer, String> result = OddIndexEntries.collectEntriesFromMap(inputMap);

        assertEquals(expected, result);
    }

    @Test
    public void testCollectEntriesFromMap_withNullKey() {
        Map<Integer, String> input = new HashMap<>();
        input.put(0, "Alice");
        input.put(null, "null key");
        input.put(3, "Charlie");
        input.put(4, "David");
        input.put(5, "Eve");

        Map<Integer, String> expected = new HashMap<>();
        expected.put(3, "Charlie");
        expected.put(5, "Eve");

        Map<Integer, String> result = OddIndexEntries.collectEntriesFromMap(input);

        assertEquals(expected, result);
    }

}