package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordLengthCombinator;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordShuffler;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordSplitter;

public class DefaultWordSplitter implements WordSplitter {

  private Set<List<String>> set;
  private WordLengthCombinator wordLengthCombinator;
  private WordShuffler wordShuffler;
  private String word;

  public DefaultWordSplitter(WordLengthCombinator wordLengthCombinator, WordShuffler wordShuffler, String word) {
    this.set = new HashSet<>();
    this.wordLengthCombinator = wordLengthCombinator;
    this.wordShuffler = wordShuffler;
    this.word = word;
  }

  public DefaultWordSplitter(Set<List<String>> set, WordLengthCombinator wordLengthCombinator,
      WordShuffler wordShuffler, String word) {
    this.set = set;
    this.wordLengthCombinator = wordLengthCombinator;
    this.wordShuffler = wordShuffler;
    this.word = word;
  }

  private List<String> loadNext() {
    List<Integer> nextSchema = wordLengthCombinator.getNext();
    if (nextSchema != null && set != null) {
      set.addAll(wordShuffler.getAllCombinations(nextSchema, word));
    }
    return pop();
  }

  public List<String> getNext() {
    return hasNext() ? pop() : loadNext();
  }

  private List<String> pop() {
    if (!hasNext()) {
      return null;
    }
    List<String> value = set.iterator().next();
    set.remove(value);
    return value;
  }

  private boolean hasNext() {
    return set != null && !set.isEmpty();
  }
}
