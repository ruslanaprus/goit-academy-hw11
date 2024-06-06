package org.example.task2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineSorterTest {

    @Test
    void testSort(){
        List<String> catsTest = Arrays.asList("Whiskers", "Pawster", "Buscuit", "Pounce", "Catsby", "Purrlock Holmes", "Catpernicus", "Picatso");
        List<String> expected = Arrays.asList("WHISKERS", "PURRLOCK HOLMES", "POUNCE", "PICATSO", "PAWSTER", "CATSBY", "CATPERNICUS", "BUSCUIT");

        List<String> sortedCats = LineSorter.sortDecreasingOrderToUppercase(catsTest);
        assertEquals(expected, sortedCats);
    }

    @Test
    public void testEmptyList() {
        List<String> testInput = Collections.emptyList();
        List<String> expected = Collections.emptyList();
        List<String> result = LineSorter.sortDecreasingOrderToUppercase(testInput);
        assertEquals(expected, result);
    }

    @Test
    public void testWithNullValues() {
        List<String> catsTest = Arrays.asList(null, "Whiskers", "Pawster", "Buscuit", "Pounce", "Catsby", "Purrlock Holmes", "Catpernicus", "Picatso");
        assertThrows(NullPointerException.class, () -> {
            LineSorter.sortDecreasingOrderToUppercase(catsTest);
        }, "A list containing null values should throw a NullPointerException");
    }

    @Test
    public void testSpecialCharacters() {
        List<String> catsTest = Arrays.asList("Whiskers!", "@Pawster", "#Buscuit", "$Pounce", "?Catsby", "!Purrlock Holmes", "{Catpernicus", "%Picatso");
        List<String> expected = Arrays.asList("WHISKERS!", "PURRLOCK HOLMES", "POUNCE", "PICATSO", "PAWSTER", "CATSBY", "CATPERNICUS", "BUSCUIT");

        List<String> actual = LineSorter.sortDecreasingOrderToUppercase(catsTest);
        assertEquals(expected, actual, "The list should handle special characters and sort them correctly");
    }
}