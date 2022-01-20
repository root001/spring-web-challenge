package com.challenge.abdulbasit.spring.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingTestII {

    @Test
    void assertionTest(){
        assertEquals("stringOne", "stringTwo", "Faliure Msg to be displayed if false.");
    }

    @Test
    void assertionTestsList(){
        List<String> expected = Arrays.asList("a", "b", "c");
        List<String> actual = Arrays.asList("a", "c", "b");
        assertEquals(expected, actual, "Faliure Msg to be displayed if false.");
    }

    @Test
    void assertionTestsArrays(){
        int[] expected = {1, 4, 7};
        int[] actual = {1, 4, 9};
        assertEquals(expected, actual, "Faliure Msg to be displayed if false.");
    }


    @Test
    void assertionTestsTrueF(){
        assertTrue(false, "The condition did not exec as expected");
        assertFalse(false);

    }

    @Test
    void assertionTestsThrows(){
        assertThrows(NullPointerException.class, null);
    }

    @Test
    void assertionTests(){
        assertAll(
                () -> assertThrows(NullPointerException.class, null),
                () -> assertFalse(false)
        );

    }

    @Test
    void assertsThatMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 5);
        map.put("d", 4);

        MatcherAssert.assertThat(map, Matchers.hasValue(5));
        MatcherAssert.assertThat(map, Matchers.hasKey("b"));
    }

    @Test
    void assertsThatListHamcrest(){
        List<String>  list = Arrays.asList("stringOne", "stringTwo", "stringThree");

        MatcherAssert.assertThat(list, Matchers.hasItem("stringTwo"));
        MatcherAssert.assertThat(list, Matchers.hasItem("b"));
    }

    @Test
    void assertsAnyOfHamcrest(){
        List<String>  list = Arrays.asList("stringOne", "stringTwo", "stringThree");

        MatcherAssert.assertThat(list, Matchers.anyOf(Matchers.hasItem("stringOne"), Matchers.hasItem("b")) );
        MatcherAssert.assertThat(list, Matchers.allOf(Matchers.hasItem("stringOne"), Matchers.hasItem(" ")) );
        MatcherAssert.assertThat(list, Matchers.containsInAnyOrder("stringOne", "stringTwo", "stringThree") );
    }
}
