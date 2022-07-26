package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordShuffler;

public class DefaultWordShufflerTest {

  private WordShuffler wordShuffler = new DefaultWordShuffler();

  @Test
  public void when9LengthWordInA333Schema_then280ResultsAreReturned() {
    String word = "abcdefghi";
    List<Integer> schema = List.of(3, 3, 3);

    assertEquals(280, wordShuffler.getAllCombinations(schema, word).size());
  }

  @Test
  public void whenSchemaIsNull_thenEmptyListIsReturned() {
    String word = "abcdefghi";
    List<Integer> schema = null;

    assertEquals(0, wordShuffler.getAllCombinations(schema, word).size());
  }

  @Test
  public void whenSchemaIsEmpty_thenEmptyListIsReturned() {
    String word = "abcdefghi";
    List<Integer> schema = List.of();

    assertEquals(0, wordShuffler.getAllCombinations(schema, word).size());
  }

  @Test
  public void whenWordIsNull_thenEmptyListIsReturned() {
    String word = null;
    List<Integer> schema = List.of(3, 3, 3);

    assertEquals(0, wordShuffler.getAllCombinations(schema, word).size());
  }

  @Test
  public void whenWordIsEmpty_thenEmptyListIsReturned() {
    String word = "";
    List<Integer> schema = List.of(3, 3, 3);

    assertEquals(0, wordShuffler.getAllCombinations(schema, word).size());
  }

}