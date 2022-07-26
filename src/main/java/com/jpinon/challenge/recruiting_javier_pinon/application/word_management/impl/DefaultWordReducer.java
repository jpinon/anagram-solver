package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordReducer;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.utils.WordUtils;

public class DefaultWordReducer implements WordReducer {

  private Set<String> set;
  private final int minSize;
  private String word;
  private int numberOfRemoves;

  public DefaultWordReducer(int minSize, String word) {
    this.minSize = minSize;
    this.word = word;
  }

  public DefaultWordReducer(Set<String> set, int minSize, String word) {
    this.set = set;
    this.minSize = minSize;
    this.word = word;
  }

  public void init() {
    numberOfRemoves = 1;

    set = new HashSet<>();
    char[] charArray = word.toCharArray();

    for (int i = 0; i < word.length() && word.length() > minSize; i++) {
      removeLetter(charArray, i, 0);
    }
  }

  private void removeLetter(char[] word, int index, int removesDone) {
    char[] newWord = WordUtils.removeValueAtIndex(word, index);

    if (!isReducible(newWord) || expectedDepthReached(removesDone)) {
      set.add(new String(newWord));
      return;
    }

    for (int i = 0; i < newWord.length && newWord.length > minSize; i++) {
      removeLetter(newWord, i, removesDone + 1);
    }

    sortSet();
  }

  private boolean isReducible(char[] word) {
    return word.length > minSize;
  }

  private boolean expectedDepthReached(int removesDone) {
    return removesDone >= numberOfRemoves - 1;
  }

  private void sortSet() {
    List<String> sorted = new ArrayList<>(set);
    sorted.sort(Comparator.comparingInt(String::length).reversed());
    set = new LinkedHashSet<>(sorted);
  }

  private String loadNext() {
    numberOfRemoves++;

    if (word.length() - numberOfRemoves < minSize) {
      return null;
    }

    for (int i = 0; i < word.length() && word.length() > minSize; i++) {
      removeLetter(word.toCharArray(), i, 0);
    }

    return pop();
  }

  public String getNext() {
    return hasNext() ? pop() : loadNext();
  }

  private String pop() {
    if (!hasNext()) {
      return null;
    }
    String value = set.iterator().next();
    set.remove(value);
    return value;
  }

  private boolean hasNext() {
    return set != null && !set.isEmpty();
  }
}
