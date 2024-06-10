/**
 * Provides utility methods for combining streams.
 */
package org.example.task5;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility class to combine two streams element-wise using a provided zipper function.
 */
public class CombineStreams {

    /**
     * Zips two streams element-wise using the provided zipper function.
     *
     * @param first The first stream to be zipped.
     * @param second The second stream to be zipped.
     * @param zipper The function that merges elements from the two streams into a single element.
     * @param <T> The type of elements in the first stream.
     * @param <U> The type of elements in the second stream.
     * @param <R> The type of elements in the resulting stream after zipping.
     * @return A stream consisting of elements resulting from applying the zipper function to corresponding elements from the two input streams.
     * @throws NullPointerException if any of the parameters are null.
     */
    public static <T, U, R> Stream<R> zip(Stream<T> first, Stream<U> second, BiFunction<T, U, R> zipper) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<U> secondIterator = second.iterator();

        Iterator<R> zippingIterator = new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return firstIterator.hasNext() && secondIterator.hasNext();
            }

            @Override
            public R next() {
                return zipper.apply(firstIterator.next(), secondIterator.next());
            }
        };

        Spliterator<R> spliterator = Spliterators.spliteratorUnknownSize(
                zippingIterator,
                Spliterator.ORDERED
        );

        return StreamSupport.stream(spliterator, false);
    }
}
