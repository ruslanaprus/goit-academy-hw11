/**
 * Provides utility methods for combining streams.
 */
package org.example.task5;

import java.util.*;
import java.util.stream.Stream;

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
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            builder.accept(iterator1.next());
            builder.accept(iterator2.next());
        }

        return builder.build();
    }
}
