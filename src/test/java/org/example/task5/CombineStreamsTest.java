package org.example.task5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CombineStreamsTest {

    @Test
    void testZip_EmptyStreams() {
        Stream<Integer> first = Stream.empty();
        Stream<String> second = Stream.empty();
        Stream<String> result = CombineStreams.zip(first, second, (a, b) -> a + ", " + b);
        assertEquals(0, result.count());
    }

    @Test
    void testZip_StreamsWithDifferentLengths() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<String> second = Stream.of("a", "b");
        Stream<String> result = CombineStreams.zip(first, second, (a, b) -> a + ", " + b);
        assertEquals(2, result.count());
    }

    @Test
    void testZip_StreamsWithSameLengths() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<String> second = Stream.of("a", "b", "c");
        Stream<String> result = CombineStreams.zip(first, second, (a, b) -> a + ", " + b);
        List<String> expected = Arrays.asList("1, a", "2, b", "3, c");
        assertEquals(expected, result.toList());
    }

    @Test
    void testZip_StreamsWithNullElements() {
        Stream<Integer> first = Stream.of(1, null, 3);
        Stream<String> second = Stream.of("a", "b", null);
        Stream<String> result = CombineStreams.zip(first, second, (a, b) -> (a != null ? a.toString() : "null") + ", " + b);
        List<String> expected = Arrays.asList("1, a", "null, b", "3, null");
        assertEquals(expected, result.toList());
    }

    @Test
    void testZip_StreamsWithDifferentTypes() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<Double> second = Stream.of(1.5, 2.5, 3.5);
        Stream<String> result = CombineStreams.zip(first, second, (a, b) -> String.valueOf(a) + ", " + String.valueOf(b));
        List<String> expected = Arrays.asList("1, 1.5", "2, 2.5", "3, 3.5");
        assertEquals(expected, result.toList());
    }

    @Test
    void testZip_CustomZipper() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<String> second = Stream.of("a", "b", "c");
        BiFunction<Integer, String, String> customZipper = (a, b) -> a.toString().repeat(a) + ", " + b;
        Stream<String> result = CombineStreams.zip(first, second, customZipper);
        List<String> expected = Arrays.asList("1, a", "22, b", "333, c");
        assertEquals(expected, result.toList());
    }
}