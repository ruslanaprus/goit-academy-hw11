
package org.example.task5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CombineStreamsTest {
    private <T> List<T> streamToList(Stream<T> stream) {
        return stream.toList();
    }

    @Test
    public void testZipBothStreamsEmpty() {
        Stream<Integer> first = Stream.empty();
        Stream<Integer> second = Stream.empty();

        List<Integer> result = streamToList(CombineStreams.zip(first, second));

        List<Integer> expected = Arrays.asList();
        assertEquals(expected, result);
    }

    @Test
    public void testZipWithEqualLengthStreams() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<Integer> second = Stream.of(4, 5, 6);

        List<Integer> result = streamToList(CombineStreams.zip(first, second));

        List<Integer> expected = Arrays.asList(1, 4, 2, 5, 3, 6);
        assertEquals(expected, result);
    }

    @Test
    public void testZipFirstStreamShorter() {
        Stream<Integer> first = Stream.of(1, 2);
        Stream<Integer> second = Stream.of(4, 5, 6);

        List<Integer> result = streamToList(CombineStreams.zip(first, second));

        List<Integer> expected = Arrays.asList(1, 4, 2, 5);
        assertEquals(expected, result);
    }

    @Test
    public void testZipSecondStreamShorter() {
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<Integer> second = Stream.of(4, 5);

        List<Integer> result = streamToList(CombineStreams.zip(first, second));

        List<Integer> expected = Arrays.asList(1, 4, 2, 5);
        assertEquals(expected, result);
    }

    @Test
    public void testZipWithStringTypes() {
        Stream<String> first = Stream.of("a", "b", "c");
        Stream<String> second = Stream.of("d", "e", "f");

        List<String> result = streamToList(CombineStreams.zip(first, second));

        List<String> expected = Arrays.asList("a", "d", "b", "e", "c", "f");
        assertEquals(expected, result);
    }
}
