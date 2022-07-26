package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordUtilsTest {

  @Test
  public void whenRemoveValueAtIndexWordIsNull_thenEmptyIsReturned() {
    char[] word = null;
    int index = 4;

    char[] result = WordUtils.removeValueAtIndex(word, index);

    assertEquals(0, result.length);
  }

  @Test
  public void whenRemoveValueAtIndexWordIsOutOfBounds_thenEmptyIsReturned() {
    char[] word = {'a', 'b', 'c'};
    int index = 4;

    char[] result = WordUtils.removeValueAtIndex(word, index);

    assertEquals(0, result.length);
  }

  @Test
  public void whenRemoveValueAtIndexInputIsOk_thenIndexIsRemoved() {
    char[] word = {'a', 'b', 'c'};
    int index = 1;

    char[] expectedWord = {'a', 'c'};

    char[] result = WordUtils.removeValueAtIndex(word, index);

    assertEquals(new String(expectedWord), new String(result));
  }

  @Test
  public void whenRemoveCharsOldWordIsNull_thenResultIsEmpty() {
    char[] word = null;
    char[] charsToRemove = {'a', 'c'};

    char[] result = WordUtils.removeChars(word, charsToRemove);

    assertEquals(0, result.length);
  }

  @Test
  public void whenRemoveCharsCharsToRemoveIsNull_thenSameWordIsReturned() {
    char[] word = {'a', 'b', 'c'};
    char[] charsToRemove = null;

    char[] expectedWord = {'a', 'b', 'c'};

    char[] result = WordUtils.removeChars(word, charsToRemove);

    assertEquals(new String(expectedWord), new String(result));
  }

  @Test
  public void whenRemoveCharsInputIsOkAndCharsMatch_thenMatchingCharsAreRemoved() {
    char[] word = {'a', 'b', 'c'};
    char[] charsToRemove = {'a', 'c'};

    char[] expectedWord = {'b'};

    char[] result = WordUtils.removeChars(word, charsToRemove);

    assertEquals(new String(expectedWord), new String(result));
  }

  @Test
  public void whenRemoveCharsInputIsOkButCharsDoNotMatch_thenSameWordIsReturned() {
    char[] word = {'a', 'b', 'c'};
    char[] charsToRemove = {'d', 'e'};

    char[] expectedWord = {'a', 'b', 'c'};

    char[] result = WordUtils.removeChars(word, charsToRemove);

    assertEquals(new String(expectedWord), new String(result));
  }

  @Test
  public void whenLettersAreInWordWordIsNull_thenFalseIsReturned() {
    char[] word = {'a', 'b', 'c'};
    char[] letters = null;

    assertFalse(WordUtils.lettersAreInWord(word, letters));
  }

  @Test
  public void whenLettersAreInWordLettersIsNull_thenFalseIsReturned() {
    char[] word = null;
    char[] letters = {'a', 'c'};

    assertFalse(WordUtils.lettersAreInWord(word, letters));
  }

  @Test
  public void whenLettersAreInWord_thenTrueIsReturned() {
    char[] word = {'a', 'b', 'c'};
    char[] letters = {'a', 'c'};

    assertTrue(WordUtils.lettersAreInWord(word, letters));
  }

  @Test
  public void whenLettersAreNotInWord_thenFalseIsReturned() {
    char[] word = {'a', 'b', 'c'};
    char[] letters = {'a', 'a'};

    assertFalse(WordUtils.lettersAreInWord(word, letters));
  }

  @Test
  public void whenSortWordByStringWordIsNull_thenEmptyIsReturned() {
    String word = null;
    String expectedResult = "";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }

  @Test
  public void whenSortWordByStringWordIsEmpty_thenEmptyIsReturned() {
    String word = "";
    String expectedResult = "";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }

  @Test
  public void whenSortWordByStringInputIsOk_thenSortedWordIsReturned() {
    String word = "hello";
    String expectedResult = "ehllo";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }

  @Test
  public void whenSortWordByCharArrayWordIsNull_thenEmptyIsReturned() {
    char[] word = null;
    String expectedResult = "";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }

  @Test
  public void whenSortWordByCharArrayWordIsEmpty_thenEmptyIsReturned() {
    char[] word = new char[0];
    String expectedResult = "";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }

  @Test
  public void whenSortWordByCharArrayInputIsOk_thenSortedWordIsReturned() {
    char[] word = {'h', 'e', 'l', 'l', 'o'};
    String expectedResult = "ehllo";

    String result = WordUtils.sortWord(word);

    assertEquals(expectedResult, result);
  }


}