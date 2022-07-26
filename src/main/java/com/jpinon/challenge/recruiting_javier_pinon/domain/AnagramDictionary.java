package com.jpinon.challenge.recruiting_javier_pinon.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.utils.WordUtils;

public class AnagramDictionary {

  private Map<String, List<String>> dictionary;

  public AnagramDictionary() {
    dictionary = new HashMap<>();
  }

  public void init(List<String> words) {
    words.forEach(this::addToDictionary);
  }

  public String findOneWordAnagram(String word, List<String> ignoredWords) {
    if (word == null || ignoredWords == null) {
      throw new IllegalArgumentException("Parameters word and ignoredWords can not be null");
    }

    final List<String> normalizedIgnoredWords = ignoredWords.stream().map(this::normalize).collect(Collectors.toList());

    List<String> result = dictionary.get(WordUtils.sortWord(normalize(word)));

    return result == null ? "" : result.stream().
        filter(thisWord -> !normalizedIgnoredWords.contains(thisWord)).findAny().orElse("");
  }

  private List<String> toList(String word) {
    List<String> list = new ArrayList<>();
    list.add(word);
    return list;
  }

  private void addToDictionary(String word) {
    if (word == null) {
      throw new IllegalArgumentException("Word must not be null");
    }

    String normalizedWord = normalize(word);

    String sortedWord = WordUtils.sortWord(normalizedWord);

    if (dictionary.containsKey(sortedWord)) {
      dictionary.get(sortedWord).add(normalizedWord);
    } else {
      dictionary.put(sortedWord, toList(normalizedWord));
    }
  }

  private String normalize(String word) {
    return word.toLowerCase();
  }
}
