package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordLengthCombinator;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordShuffler;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordSplitter;

public class DefaultWordSplitterTest {

  private static final int MIN_SIZE = 3;

  private WordSplitter wordSplitter;

  private WordLengthCombinator wordLengthCombinator = Mockito.mock(DefaultWordLengthCombinator.class);

  private WordShuffler wordShuffler = Mockito.mock(DefaultWordShuffler.class);


  @Test
  public void whenSetIsFilled_thenGetNextReturnsSomeValue() {
    List<String> firstValue = List.of("hello", "world");
    List<String> secondValue = List.of("world", "hello");
    Set<List<String>> set = new HashSet<>(List.of(firstValue, secondValue));
    wordSplitter = new DefaultWordSplitter(set, wordLengthCombinator, wordShuffler, "");

    List<String> result = wordSplitter.getNext();

    assertTrue(firstValue.equals(result) || secondValue.equals(result));
  }

  @Test
  public void whenSetIsEmpty_thenGetNextIsNull() {
    Set<List<String>> set = new HashSet<>();
    wordSplitter = new DefaultWordSplitter(set, wordLengthCombinator, wordShuffler, "");

    assertNull(wordSplitter.getNext());
  }

  @Test
  public void whenSetIsNull_thenGetNextIsNull() {
    Set<List<String>> set = null;
    wordSplitter = new DefaultWordSplitter(set, wordLengthCombinator, wordShuffler, "");

    assertNull(wordSplitter.getNext());
  }

  @Test
  public void whenWordSizeIsGreaterThanMinSize_thenSetIsFilled() {
    String word = "abcdefg";
    wordSplitter = new DefaultWordSplitter(wordLengthCombinator, wordShuffler, word);
    Set<List<String>> expectedResult = Set.of(List.of("abcd", "efg"),
        List.of("bcde", "afg"), List.of("bcdf", "aeg"), List.of("bcdg", "aef"),
        List.of("acde", "bfg"), List.of("acdf", "beg"), List.of("acdg", "bef"),
        List.of("abde", "cfg"), List.of("abdf", "ceg"), List.of("abdg", "cef"),
        List.of("abce", "dfg"), List.of("abcf", "deg"), List.of("abcg", "def"));

    List<Integer> mockedSchema = List.of(4, 3);
    Mockito.when(wordLengthCombinator.getNext()).thenReturn(mockedSchema);
    Mockito.when(wordShuffler.getAllCombinations(mockedSchema, word)).thenReturn(expectedResult);

    List<List<String>> result = new ArrayList<>();
    List<String> iteration = wordSplitter.getNext();

    while (iteration != null) {
      result.add(iteration);

      Mockito.when(wordLengthCombinator.getNext()).thenReturn(null);

      iteration = wordSplitter.getNext();
    }

    assertEquals(expectedResult.size(), result.size());
    assertTrue(result.containsAll(expectedResult));
  }

}