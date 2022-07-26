package com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordShuffler;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.utils.WordUtils;

public class DefaultWordShuffler implements WordShuffler {

  private Set<List<String>> set;
  private List<Integer> schema;

  public Set<List<String>> getAllCombinations(List<Integer> schema, String word) {

    this.schema = schema;
    set = new HashSet<>();

    if (!combinationsCanBeMade(schema, word)) {
      return set;
    }

    Set<String> thisSet = new HashSet<>();
    int firstWordLength = schema.get(0);

    getAllWordCombinations(thisSet, word.toCharArray(), new char[firstWordLength], 0, word.length() - 1, 0,
        firstWordLength);
    thisSet.forEach(value -> nextWord(0, value, word.toCharArray(), ""));

    return set;
  }

  private void nextWord(int wordNum, String word, char[] oldPool, String resultSoFar) {

    Set<String> thisSet = new HashSet<>();
    char[] data = new char[schema.get(wordNum + 1)];
    char[] pool = WordUtils.removeChars(oldPool, word.toCharArray());
    String thisResult = resultSoFar + word;

    if (isLastWord(wordNum)) {
      addCombination(thisResult + new String(pool));
      return;
    }

    getAllWordCombinations(thisSet, pool, data, 0, pool.length - 1, 0, schema.get(wordNum));
    thisSet.forEach(nextWord -> nextWord(wordNum + 1, nextWord, pool, thisResult));

  }

  private void getAllWordCombinations(Set<String> thisSet, char pool[], char word[], int leftBound, int rightBound,
      int index, int wordLength) {
    if (index == wordLength) {
      if (WordUtils.lettersAreInWord(pool, word)) {
        thisSet.add(WordUtils.sortWord(word));
      }
      return;
    }

    for (int i = leftBound; !poolOrWordHaveReachedItsLimits(i, rightBound, index, wordLength); i++) {
      word[index] = pool[i];
      getAllWordCombinations(thisSet, pool, word, i + 1, rightBound, index + 1, wordLength);
    }
  }

  private void addCombination(String word) {
    set.add(toSolutionFormat(word.toCharArray()).stream().sorted().collect(Collectors.toList()));
  }

  private List<String> toSolutionFormat(char[] word) {
    int leftBound;
    int rightBound = 0;
    List<java.lang.String> words = new ArrayList<>();

    for (int i = 0; i < schema.size(); i++) {

      leftBound = i == 0 ? 0 : rightBound;
      rightBound += i == 0 ? schema.get(0) : schema.get(i);

      words.add(WordUtils.sortWord(Arrays.copyOfRange(word, leftBound, rightBound)));
    }

    return words;
  }

  private boolean poolOrWordHaveReachedItsLimits(int poolIterator, int poolLength, int wordIterator, int wordLength) {
    return poolIterator > poolLength || poolLength < wordLength - wordIterator;
  }

  private boolean combinationsCanBeMade(List<Integer> schema, String word) {
    return word != null && !word.isBlank() && schema != null && schema.size() >= 2;
  }

  private boolean isLastWord(int wordNum) {
    return wordNum + 1 == schema.size() - 1;
  }
}
