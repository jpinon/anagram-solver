package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordReducer;

public class DefaultWordReducerTest {

  private static final int MIN_SIZE = 3;

  private WordReducer wordReducer;

  @Test
  public void whenSetIsFilled_thenGetNextReturnsSomeValue() {
    String firstValue = "hello";
    String secondValue = "world";
    Set<String> set = new HashSet<>(List.of(firstValue, secondValue));
    wordReducer = new DefaultWordReducer(set, MIN_SIZE, "");

    String result = wordReducer.getNext();

    assertTrue(firstValue.equals(result) || secondValue.equals(result));
  }

  @Test
  public void whenSetIsEmpty_thenGetNextIsNull() {
    Set<String> set = new HashSet<>();
    wordReducer = new DefaultWordReducer(set, MIN_SIZE, "");

    assertNull(wordReducer.getNext());
  }

  @Test
  public void whenSetIsNull_thenGetNextIsNull() {
    Set<String> set = null;
    wordReducer = new DefaultWordReducer(set, MIN_SIZE, "");

    assertNull(wordReducer.getNext());
  }

  @Test
  public void whenWordSizeIsLesserEqualThanMinSize_thenSetIsEmpty() {
    String word = "abc";
    wordReducer = new DefaultWordReducer(MIN_SIZE, word);

    wordReducer.init();

    assertNull(wordReducer.getNext());
  }

  @Test
  public void whenWordSizeIsGreaterThanMinSize_thenSetIsFilled() {
    String word = "abcde";
    wordReducer = new DefaultWordReducer(MIN_SIZE, word);
    List<String> expectedResult = List.of("abcd", "abce", "abde", "acde", "bcde",
        "abc", "abd", "acd", "bcd",
        "abe", "ace", "bce",
        "ade", "bde",
        "cde");

    wordReducer.init();

    List<String> result = new ArrayList<>();

    String iteration = wordReducer.getNext();

    while (iteration != null) {
      result.add(iteration);
      iteration = wordReducer.getNext();
    }

    assertEquals(expectedResult.size(), result.size());
    assertTrue(result.containsAll(expectedResult));
  }

}