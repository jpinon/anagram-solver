package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordLengthCombinator;

public class DefaultWordLengthCombinatorTest {

  private static final int MIN_SIZE = 3;

  private WordLengthCombinator wordLengthCombinator;

  @Test
  public void whenSetIsFilled_thenGetNextReturnsSomeValue() {
    List<Integer> firstValue = List.of(3, 5);
    List<Integer> secondValue = List.of(4, 4);
    Set<List<Integer>> set = new HashSet<>(List.of(firstValue, secondValue));
    wordLengthCombinator = new DefaultWordLengthCombinator(set, MIN_SIZE, "");

    List<Integer> result = wordLengthCombinator.getNext();

    assertTrue(firstValue.equals(result) || secondValue.equals(result));
  }

  @Test
  public void whenSetIsEmpty_thenGetNextIsNull() {
    Set<List<Integer>> set = new HashSet<>();
    wordLengthCombinator = new DefaultWordLengthCombinator(set, MIN_SIZE, "");
    assertNull(wordLengthCombinator.getNext());
  }

  @Test
  public void whenSetIsNull_thenGetNextIsNull() {
    Set<List<Integer>> set = null;
    wordLengthCombinator = new DefaultWordLengthCombinator(set, MIN_SIZE, "");

    assertNull(wordLengthCombinator.getNext());
  }

  @Test
  public void when9LengthWord_thenAllCombinationsAreGenerated() {
    String word = "abcdefghi";
    Set<List<Integer>> expectedResult = Set.of(List.of(3, 3, 3), List.of(3, 6), List.of(4, 5));

    wordLengthCombinator = new DefaultWordLengthCombinator(MIN_SIZE, word);

    wordLengthCombinator.init();

    List<List<Integer>> result = new ArrayList<>();

    List<Integer> iteration = wordLengthCombinator.getNext();

    while (iteration != null) {
      result.add(iteration);
      iteration = wordLengthCombinator.getNext();
    }

    assertEquals(expectedResult.size(), result.size());
    assertTrue(result.containsAll(expectedResult));
  }


}