package org.example.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CongruentialGeneratorTest {

    private static final long A = 25214903917L;
    private static final long C = 11L;
    private static final long M = 1L << 48;

    private Supplier<Long> seedSupplier;

    @BeforeEach
    public void setUp() {
        seedSupplier = () -> 0L;
    }

    @Test
    public void testGenerateStreamWithDefaultSeedSupplier() {
        long a = 25214903917L;
        long c = 11L;
        long m = 1L << 48;

        Stream<Long> stream = CongruentialGenerator.generateStream(a, c, m);

        List<Long> generatedSequence = stream.limit(10).collect(Collectors.toList());

        assertEquals(10, generatedSequence.size());
    }

    @Test
    public void testGenerateRandomStream_FirstTenNumbers() {
        Stream<Long> randomStream = CongruentialGenerator.generateStream(seedSupplier, A, C, M);
        long[] expectedNumbers = {
                0L,
                11L,
                277363943098L,
                11718085204285L,
                49720483695876L,
                102626409374399L,
                25707281917278L,
                25979478236433L,
                137139456763464L,
                148267022728371L
        };
        long[] actualNumbers = randomStream.limit(10).mapToLong(Long::longValue).toArray();

        System.out.println("expectedNumbers = " + Arrays.toString(expectedNumbers));
        System.out.println("actualNumbers = " + Arrays.toString(actualNumbers));

        assertArrayEquals(expectedNumbers, actualNumbers);
    }

    @Test
    public void testGenerateRandomStream_ParameterValidation() {
        // Test for m <= 0
        assertThrows(IllegalArgumentException.class, () ->
                CongruentialGenerator.generateStream(seedSupplier, A, C, -1L)
        );

        // Test for a <= 0
        assertThrows(IllegalArgumentException.class, () ->
                CongruentialGenerator.generateStream(seedSupplier, 0L, C, M)
        );

        // Test for a >= m
        assertThrows(IllegalArgumentException.class, () ->
                CongruentialGenerator.generateStream(seedSupplier, M, C, M)
        );

        // Test for c < 0
        assertThrows(IllegalArgumentException.class, () ->
                CongruentialGenerator.generateStream(seedSupplier, A, -1L, M)
        );

        // Test for c >= m
        assertThrows(IllegalArgumentException.class, () ->
                CongruentialGenerator.generateStream(seedSupplier, A, M, M)
        );
    }

    @Test
    public void testNextNumber() {
        assertEquals(11L, CongruentialGenerator.nextValue(0L, A, C, M));
        assertEquals(277363943098L, CongruentialGenerator.nextValue(11L, A, C, M));
        assertEquals(11718085204285L, CongruentialGenerator.nextValue(277363943098L, A, C, M));
    }
}