package com.validity.duplicates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.codec.language.DoubleMetaphone;


class DuplicatesApplicationTests {

    @Test
    void testLevenshtein(){
        LevenshteinDistance testLevenshtein = new LevenshteinDistance();
        Assertions.assertEquals(testLevenshtein.apply("", ""), 0);
        Assertions.assertEquals(testLevenshtein.apply("", "a"), 1);
        Assertions.assertEquals(testLevenshtein.apply("boat", "goat"), 1);
        Assertions.assertEquals(testLevenshtein.apply("flaw", "lawn"), 2);
        Assertions.assertEquals(testLevenshtein.apply("kitten", "sitting"), 3);
        Assertions.assertEquals(testLevenshtein.apply("intention", "execution"), 5);
    }

    @Test
    void testMetaphone(){
        DoubleMetaphone testMetaphone = new DoubleMetaphone();
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("John", "john"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("type", "type"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("judge", "juge"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("knock", "nock"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("white", "wite"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("pair", "pear"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("iPhone11", "iPhone"),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("The End", "The End."),
            true);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("bookkeeper", "book keeper"),
            false);
        Assertions.assertEquals(testMetaphone.isDoubleMetaphoneEqual("a elephant", "an elephant"),
            false);
    }
}
