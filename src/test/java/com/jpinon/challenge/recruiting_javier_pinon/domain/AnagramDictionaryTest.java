package com.jpinon.challenge.recruiting_javier_pinon.domain;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AnagramDictionaryTest {

  @Test
  public void whenTheresAnAnagramInDictionary_thenIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World", "Listen");
    String word = "Silent";
    List<String> ignoredWords = List.of("Silent");
    String expectedAnagram = "listen";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenThereIsAnAnagramInDictionaryAndIgnoredWordsIsEmpty_thenIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World");
    String word = "Hello";
    List<String> ignoredWords = List.of();
    String expectedAnagram = "hello";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenSameWordIsInDictionaryAndAlsoInIgnoredWordsButThereIsAnAlternativeAnagram_thenAlternativeIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World", "Listen", "Silent");
    String word = "Listen";
    List<String> ignoredWords = List.of("Listen");
    String expectedAnagram = "silent";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenSameWordIsInDictionaryAndAlsoInIgnoredWordsAndThereIsNoAlternativeAnagram_thenEmptyIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World", "Listen");
    String word = "Listen";
    List<String> ignoredWords = List.of("Listen");
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenThereIsNoAnagramInDictionary_thenEmptyIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World");
    String word = "Chocolate";
    List<String> ignoredWords = List.of("Chocolate");
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenWordIsEmpty_thenEmptyIsReturned() {
    List<String> dictionaryInput = List.of("Hello", "World");
    String word = "";
    List<String> ignoredWords = List.of();
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenWordIsNull_thenIllegalArgumentException() {
    List<String> dictionaryInput = List.of("Hello", "World");
    String word = null;
    List<String> ignoredWords = List.of();
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test
  public void whenDictionaryIsEmpty_thenEmptyIsReturned() {
    List<String> dictionaryInput = List.of();
    String word = "chocolate";
    List<String> ignoredWords = List.of("Chocolate");
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenIgnoredWordsIsNull_thenIllegalArgumentException() {
    List<String> dictionaryInput = List.of("Hello", "World");
    String word = "Chocolate";
    List<String> ignoredWords = null;
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(dictionaryInput, word, ignoredWords));
  }

  private String testLogic(List<String> dictionaryInput, String word, List<String> ignoredWords) {
    AnagramDictionary anagramDictionary = new AnagramDictionary();
    anagramDictionary.init(dictionaryInput);

    return anagramDictionary.findOneWordAnagram(word, ignoredWords);
  }

}