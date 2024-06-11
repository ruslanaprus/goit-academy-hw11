/**
 * Provides utility methods for combining streams.
 */
package org.example.task5;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    //Alternative solutions:

    public static <T> Stream<T> zipWithOptional(Stream<T> first, Stream<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);

        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        class Pair {
            final Optional<T> firstElem;
            final Optional<T> secondElem;

            Pair(Optional<T> firstElem, Optional<T> secondElem) {
                this.firstElem = firstElem;
                this.secondElem = secondElem;
            }

            boolean hasBoth() {
                return firstElem.isPresent() && secondElem.isPresent();
            }
        }

        Pair initialPair = new Pair(
                iterator1.hasNext() ? Optional.of(iterator1.next()) : Optional.empty(),
                iterator2.hasNext() ? Optional.of(iterator2.next()) : Optional.empty()
        );

        return Stream.iterate(initialPair, pair -> {
                    Optional<T> nextFirst = iterator1.hasNext() ? Optional.of(iterator1.next()) : Optional.empty();
                    Optional<T> nextSecond = iterator2.hasNext() ? Optional.of(iterator2.next()) : Optional.empty();
                    return new Pair(nextFirst, nextSecond);
                })
                .takeWhile(Pair::hasBoth)
                .flatMap(pair -> Stream.of(pair.firstElem.get(), pair.secondElem.get()));
    }

    public static <T, U, R> Stream<R> zipBiFunction(Stream<T> first, Stream<U> second, BiFunction<T, U, R> zipper) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<U> secondIterator = second.iterator();

        Iterable<R> iterable = () -> new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return firstIterator.hasNext() && secondIterator.hasNext();
            }

            @Override
            public R next() {
                return zipper.apply(firstIterator.next(), secondIterator.next());
            }
        };

        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
