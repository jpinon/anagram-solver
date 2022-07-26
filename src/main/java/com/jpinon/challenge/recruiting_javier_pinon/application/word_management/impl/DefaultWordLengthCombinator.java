package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordLengthCombinator;

public class DefaultWordLengthCombinator implements WordLengthCombinator {

  private Set<List<Integer>> set;
  private int minSize;
  private String word;

  public DefaultWordLengthCombinator(int minSize, String word) {
    this.set = new HashSet<>();
    this.minSize = minSize;
    this.word = word;
  }

  public DefaultWordLengthCombinator(Set<List<Integer>> set, int minSize, String word) {
    this.set = set;
    this.minSize = minSize;
    this.word = word;
  }

  public void init() {
    populateWordsLengthCombinations(word.length(), word.length() / minSize);
  }

  private void populateWordsLengthCombinations(int wordLength, int wordsNum) {

    if (wordsNum <= 1) {
      return;
    }

    List<Integer> reference = initReferenceList(wordLength, wordsNum);

    addAllCombinations(reference, wordsNum);

    populateWordsLengthCombinations(wordLength, wordsNum - 1);
  }

  private List<Integer> initReferenceList(int wordLength, int wordsNum) {
    List<Integer> reference = new ArrayList<>();
    int remainder = wordLength - (minSize * wordsNum);

    reference.add(minSize + remainder);
    for (int i = 0; i < wordsNum - 1; i++) {
      reference.add(minSize);
    }

    return reference;
  }

  private void addAllCombinations(List<Integer> combination, int wordsNum) {

    List<Integer> thisCombination = new ArrayList<>(combination);

    for (int i = 1; i < wordsNum; i++) {
      if (combinationHasReachedItsEnd(thisCombination.get(0))) {
        addCombination(thisCombination);
        return;
      }

      combination = new ArrayList<>(thisCombination);

      combination.set(0, thisCombination.get(0) - 1);
      combination.set(i, thisCombination.get(i) + 1);

      addAllCombinations(combination, wordsNum);

      addCombination(thisCombination);

    }
  }

  private boolean combinationHasReachedItsEnd(int combination) {
    return combination <= minSize;
  }

  private void addCombination(List<Integer> combination) {
    List<Integer> combinationCopy = new ArrayList<>(combination);
    Collections.sort(combinationCopy);
    set.add(combinationCopy);
  }

  public List<Integer> getNext() {
    return hasNext() ? pop() : null;
  }

  private boolean hasNext() {
    return set != null && !set.isEmpty();
  }

  private List<Integer> pop() {
    List<Integer> value = set.iterator().next();
    set.remove(value);
    return value;
  }
}
