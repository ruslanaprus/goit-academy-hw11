/**
 * Provides utility methods for combining streams.
 */
package org.example.task5;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Utility class to combine two streams element-wise using a provided zipper function.
 */
public class CombineStreams {

    /**
     * Zips two streams into a single stream by alternating elements from both streams.
     *
     * <p>
     * The resulting stream contains elements from the first stream and the second stream,
     * in an alternating sequence. The resulting stream will only be as long as the shortest
     * input stream.
     * </p>
     *
     * @param <T>    the type of elements in the streams
     * @param first  the first stream to be zipped
     * @param second the second stream to be zipped
     * @return a stream containing elements from the first and second stream, zipped together
     * @throws NullPointerException if either of the streams is null
     */

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        var firstIterator = first.iterator();
        var secondIterator = second.iterator();

        return Stream.generate(() -> {
                    if (firstIterator.hasNext() && secondIterator.hasNext()) {
                        T firstElement = firstIterator.next();
                        T secondElement = secondIterator.next();
                        return Stream.of(firstElement, secondElement);
                    } else {
                        return null;
                    }
                })
                .takeWhile(pair -> pair != null)
                .flatMap(pair -> pair);
    }
}
