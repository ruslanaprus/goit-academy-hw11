package org.example.task5;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CombineStreams {
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
