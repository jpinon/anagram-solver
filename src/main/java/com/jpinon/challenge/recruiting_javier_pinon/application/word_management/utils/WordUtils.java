package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordUtils {

  public static char[] removeValueAtIndex(char[] word, int index) {

    if (word == null || index < 0 || index > word.length) {
      return new char[0];
    }

    char[] newWord = new char[word.length - 1];

    for (int i = 0, k = 0; i < word.length; i++) {
      if (i != index) {
        newWord[k++] = word[i];
      }
    }

    return newWord;
  }

  public static char[] removeChars(char[] oldWord, char[] charsToRemove) {

    if (oldWord == null) {
      return new char[0];
    }

    if (charsToRemove == null) {
      return oldWord;
    }

    char[] newWord = Arrays.copyOf(oldWord, oldWord.length);

    for (int i = 0; i < charsToRemove.length; i++) {
      for (int j = 0; j < oldWord.length; j++) {
        if (charsToRemove[i] == newWord[j]) {
          newWord = WordUtils.removeValueAtIndex(newWord, j);
          break;
        }
      }
    }

    return newWord;
  }

  public static boolean lettersAreInWord(char[] word, char[] letters) {
    if (word == null || letters == null) {
      return false;
    }

    Set<Integer> indexes = new HashSet<>();

    for (int i = 0; i < letters.length; i++) {
      for (int j = 0; j < word.length; j++) {
        if (letters[i] == word[j]) {
          if (!indexes.contains(j)) {
            indexes.add(j);
            break;
          }
        }
      }
    }

    return indexes.size() >= letters.length;
  }

  public static String sortWord(String word) {
    if (word == null) {
      return "";
    }

    char[] charArray = word.toCharArray();
    Arrays.sort(charArray);

    return new String(charArray);
  }

  public static String sortWord(char[] word) {
    if (word == null) {
      return "";
    }

    Arrays.sort(word);

    return new String(word);
  }

}
