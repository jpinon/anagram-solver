package com.jpinon.challenge.recruiting_javier_pinon.application.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jpinon.challenge.recruiting_javier_pinon.application.port.AnagramSolver;
import com.jpinon.challenge.recruiting_javier_pinon.application.port.DictionaryRepository;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordLengthCombinator;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordReducer;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordShuffler;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.WordSplitter;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl.DefaultWordLengthCombinator;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl.DefaultWordReducer;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl.DefaultWordShuffler;
import com.jpinon.challenge.recruiting_javier_pinon.application.word_management.impl.DefaultWordSplitter;
import com.jpinon.challenge.recruiting_javier_pinon.domain.AnagramDictionary;

public class AnagramSolverImpl implements AnagramSolver {

  private static final String DEFAULT_PHRASE = "";
  private static final String NO_ANAGRAM_SOLUTION = "";
  private static final int MIN_ANAGRAM_SIZE = 3;

  private final AnagramDictionary anagramDictionary;
  private final DictionaryRepository dictionaryRepository;

  public AnagramSolverImpl(DictionaryRepository dictionaryRepository) {
    this.anagramDictionary = new AnagramDictionary();
    this.dictionaryRepository = dictionaryRepository;
  }

  public AnagramSolverImpl(AnagramDictionary anagramDictionary, DictionaryRepository dictionaryRepository) {
    this.anagramDictionary = anagramDictionary;
    this.dictionaryRepository = dictionaryRepository;
  }

  public void initDictionary(String filesPath) {
    List<String> dictionaryWords = dictionaryRepository.getDictionaryWords(filesPath);
    anagramDictionary.init(dictionaryWords);
  }

  public String solveAnagram(String phrase) {
    String normalizedPhrase = normalizePhrase(phrase);
    final List<String> ignoredWords = getWords(phrase);

    WordReducer wordReducer = new DefaultWordReducer(MIN_ANAGRAM_SIZE, normalizedPhrase);
    wordReducer.init();

    String anagram = NO_ANAGRAM_SOLUTION;

    while (!isSolution(anagram) && solutionIsAchievable(normalizedPhrase)) {

      anagram = anagramDictionary.findOneWordAnagram(normalizedPhrase, ignoredWords);

      if (isSolution(anagram)) {
        return anagram;
      }

      anagram = solveMultiWordAnagram(anagramDictionary, normalizedPhrase, ignoredWords);

      normalizedPhrase = wordReducer.getNext();
    }

    return anagram;
  }

  private String solveMultiWordAnagram(AnagramDictionary anagramDictionary, String phrase, List<String> ignoredWords) {
    boolean solutionFound = false;
    List<String> anagrams = new ArrayList<>();

    WordSplitter wordSplitter = initializeWordSplitter(phrase);

    List<String> candidateWords = wordSplitter.getNext();

    while (!solutionFound && thereIsASolutionCandidate(candidateWords)) {

      anagrams = candidateWords.stream().map(word -> anagramDictionary.findOneWordAnagram(word, ignoredWords))
          .collect(Collectors.toList());

      solutionFound = anagrams.stream().allMatch(this::isSolution);

      candidateWords = wordSplitter.getNext();
    }

    return solutionFound ? String.join(" ", anagrams) : NO_ANAGRAM_SOLUTION;
  }

  private WordSplitter initializeWordSplitter(String phrase) {
    WordLengthCombinator wordLengthCombinator = new DefaultWordLengthCombinator(MIN_ANAGRAM_SIZE, phrase);
    wordLengthCombinator.init();
    WordShuffler wordShuffler = new DefaultWordShuffler();
    return new DefaultWordSplitter(wordLengthCombinator, wordShuffler, phrase);
  }

  private String normalizePhrase(String phrase) {
    return phrase == null ? DEFAULT_PHRASE : phrase.toLowerCase().replaceAll("\\s", "");
  }

  private List<String> getWords(String phrase) {
    return phrase == null ? new ArrayList<>()
        : Arrays.stream(phrase.toLowerCase().trim().split(" "))
            .filter(word -> !word.isBlank())
            .collect(Collectors.toList());
  }

  private boolean isSolution(String anagram) {
    return !anagram.equals(NO_ANAGRAM_SOLUTION);
  }

  private boolean solutionIsAchievable(String word) {
    return word != null && word.length() >= MIN_ANAGRAM_SIZE;
  }

  private boolean thereIsASolutionCandidate(List<String> candidate) {
    return candidate != null;
  }

}
