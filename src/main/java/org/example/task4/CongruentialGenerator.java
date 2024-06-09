package org.example.task4;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CongruentialGenerator {
    private static final Supplier<Long> defaultSeedSupplier = System::currentTimeMillis;

    public static Stream<Long> generateStream(long a, long c, long m) {
        validateParameters(a, c, m);
        return Stream.iterate(defaultSeedSupplier.get(), x -> nextValue(x, a, c, m));
    }

    public static Stream<Long> generateStream(Supplier<Long> seedSupplier, long a, long c, long m) {
        validateParameters(a, c, m);
        return Stream.iterate(seedSupplier.get(), x -> nextValue(x, a, c, m));
    }

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

    public static long nextValue(long current, long a, long c, long m) {
        return (a * current + c) % m;
    }
}
