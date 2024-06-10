/**
 * Provides utility methods for generating streams of random values using a congruential generator algorithm.
 */
package org.example.task4;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Utility class for generating streams of random values using a congruential generator algorithm.
 */
public class CongruentialGenerator {

    private static final Supplier<Long> defaultSeedSupplier = System::currentTimeMillis;

    /**
     * Generates a stream of numbers using the congruential generator algorithm with default seed.
     *
     * @param a The multiplier.
     * @param c The increment.
     * @param m The modulus.
     * @return A stream of numbers generated using the congruential generator algorithm.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public static Stream<Long> generateStream(long a, long c, long m) {
        validateParameters(a, c, m);
        return Stream.iterate(defaultSeedSupplier.get(), x -> nextValue(x, a, c, m));
    }

    /**
     * Generates a stream of numbers using the congruential generator algorithm with a custom seed.
     *
     * @param seedSupplier A supplier providing the initial seed for the generator.
     * @param a The multiplier.
     * @param c The increment.
     * @param m The modulus.
     * @return A stream of numbers generated using the congruential generator algorithm.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public static Stream<Long> generateStream(long a, long c, long m, Supplier<Long> seedSupplier) {
        validateParameters(a, c, m);
        return Stream.iterate(seedSupplier.get(), x -> nextValue(x, a, c, m));
    }

    /**
     * Validates the parameters for the congruential generator algorithm.
     *
     * @param a The multiplier.
     * @param c The increment.
     * @param m The modulus.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    private static void validateParameters(long a, long c, long m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Modulus 'm' must be a positive number");
        }
        if (a <= 0 || a >= m) {
            throw new IllegalArgumentException("Multiplier 'a' must be in the range 1 <= a < m");
        }
        if (c < 0 || c >= m) {
            throw new IllegalArgumentException("Increment 'c' must be in the range 0 <= c < m");
        }
    }

    /**
     * Computes the next value in the congruential generator sequence.
     *
     * @param current The current value.
     * @param a The multiplier.
     * @param c The increment.
     * @param m The modulus.
     * @return The next value in the congruential generator sequence.
     */
    public static long nextValue(long current, long a, long c, long m) {
        return (a * current + c) % m;
    }
}
