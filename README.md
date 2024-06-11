# Functional Utilities for Collections and Streams
## Overview

This project, created as a solution for the GoIT Academy Module 11 [Functional Programming, Stream API] hometask, offers utility methods designed to perform various operations on collections, lists, maps, strings, and streams. The project is organized into five main tasks, each encapsulated in its own package, ensuring modularity and ease of use.

This set of tools uses functional programming and the Stream API to make complex data handling and processing easier.

## Packages

- Task 1: [OddIndexEntries](#oddindexentries)
- Task 2: [LineSorter](#linesorter)
- Task 3: [NumberSorter](#numbersorter)
- Task 4: [CongruentialGenerator](#congruentialgenerator)
- Task 5: [CombineStreams](#combinestreams)

## OddIndexEntries

The `OddIndexEntries` class provides utility methods for collecting entries from generic collections, lists, and maps based on their odd indices or keys.

### Methods

- `static <T> List<T> collectGenericEntries(Collection<T> collection)`: Collects entries from a collection based on their odd indices.
- `static String collectEntriesToString(List<String> list)`: Collects entries from a list of strings based on their odd indices and joins them into a single string.
- `static Map<Integer, String> collectEntriesFromMap(Map<Integer, String> map)`: Collects entries from a map based on their odd keys.

### Example Usage

```java
List<String> list = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
List<String> result = OddIndexEntries.collectGenericEntries(list);
// result: ["Bob", "David", "Frank"]

Map<Integer, String> map = Map.of(1, "Alice", 2, "Bob", 3, "Eve");
Map<Integer, String> filteredMap = OddIndexEntries.collectEntriesFromMap(map);
// filteredMap: {1=Alice, 3=Eve}
```
## LineSorter

The `LineSorter` class provides utility methods for sorting lines in decreasing order and converting them to uppercase.

### Methods
`static List<String> sortDecreasingOrderToUppercase(List<String> list)`: Sorts lines in decreasing order of their natural ordering (case-insensitive) and converts them to uppercase.

### Example Usage

```Java
List<String> lines = Arrays.asList("Whiskers", "Biscuit", "Catsby", "Purrlock Holmes", "Catpernicus");
List<String> sortedLines = LineSorter.sortDecreasingOrderToUppercase(lines);
// sortedLines: ["WHISKERS", "PURRLOCK HOLMES", "CATSBY", "CATPERNICUS", "BISCUIT"]
```
## NumberSorter

The `NumberSorter` class provides utility methods for collecting and sorting numbers from strings.

### Methods

`static List<Integer> collectAndSortNumbers(List<String> list)`: Given a collection of strings, the method extracts all numbers, sorts them, and returns them as a List of numeric values.

### Example Usage

```Java
List<String> stringsWithNumbers = Arrays.asList("a1", "b42", "c3");
List<Integer> sortedNumbers = NumberSorter.collectAndSortNumbers(stringsWithNumbers);
// sortedNumbers: [1, 3, 42]
```

## CongruentialGenerator

The `CongruentialGenerator` class provides utility methods for generating streams of random values using a congruential generator algorithm.

### Methods

Using `Stream.iterate`, these methods can generate an infinite stream of random numbers using a linear congruent generator. It implements a method that takes parameters `a`, `c`, and `m` as input and returns a `Stream<Long>`. The generator formula is `x[n + 1] = (a * x[n] + c) % m`.

`static Stream<Long> generateStream(long a, long c, long m)`: Generates a stream of numbers using the congruential generator algorithm with default seed - `currentTimeMillis`

`static Stream<Long> generateStream(long a, long c, long m, Supplier<Long> seedSupplier)`: Generates a stream of numbers using the congruential generator algorithm with a custom seed.
### Example Usage

```Java
Stream<Long> randomNumbers = CongruentialGenerator.generateStream(25214903917L, 11, (1L << 48));
// Generates an infinite stream of random numbers

Supplier<Long> customSeed = () -> 123456789L;
Stream<Long> customRandomNumbers = CongruentialGenerator.generateStream(customSeed, 25214903917L, 11, (1L << 48));
// Generates an infinite stream of random numbers with a custom seed
```

## CombineStreams

The `CombineStreams` class provides utility methods for combining two streams element-wise using a provided zipper function.

### Methods

`static <T> Stream<T> zip(Stream<T> first, Stream<T> second)`: Takes two streams and merges them element-wise into a single stream, stopping when one of the streams runs out of elements.


### Example Usage

```Java
Stream<Integer> first = Stream.of(1, 2, 3);
Stream<Integer> second = Stream.of(4, 5, 6, 7);
List<Integer> zippedStream = CombineStreams.zip(first, second).toList();
// zippedStream: [1, 4, 2, 5, 3, 6]
```