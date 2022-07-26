package com.jpinon.challenge.recruiting_javier_pinon.application.adapter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.jpinon.challenge.recruiting_javier_pinon.application.port.AnagramSolver;
import com.jpinon.challenge.recruiting_javier_pinon.application.port.DictionaryRepository;
import com.jpinon.challenge.recruiting_javier_pinon.domain.AnagramDictionary;
import com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file.FileDictionaryRepository;

public class AnagramSolverImplTest {

  private DictionaryRepository dictionaryRepository = mock(FileDictionaryRepository.class);

  private AnagramDictionary anagramDictionary = mock(AnagramDictionary.class);

  private AnagramSolver anagramSolver = new AnagramSolverImpl(anagramDictionary, dictionaryRepository);

  @Test
  public void whenInitDictionaryIsCalled_thenLogicIsPerformed() {
    String filePath = "/file/path/";
    List<String> dictionaryWords = List.of("Hello", "World");

    when(dictionaryRepository.getDictionaryWords(filePath)).thenReturn(dictionaryWords);

    anagramSolver.initDictionary(filePath);

    verify(anagramDictionary, times(1)).init(dictionaryWords);
  }

  @Test
  public void whenPhraseIsNormalizedAndCanBeSolvedByOneWordMethod_thenIsReturned() {
    String phrase = "listen";
    String normalizedPhrase = "listen";
    List<String> ignoredWords = List.of("listen");
    String expectedAnagram = "silent";

    assertEquals(expectedAnagram, testLogic(phrase, normalizedPhrase, ignoredWords, expectedAnagram));
  }

  @Test
  public void whenPhraseIsNotNormalizedAndCanBeSolvedByOneWordMethod_thenIsReturned() {
    String phrase = "liS Ten ";
    String normalizedPhrase = "listen";
    List<String> ignoredWords = List.of("lis", "ten");
    String expectedAnagram = "silent";

    assertEquals(expectedAnagram, testLogic(phrase, normalizedPhrase, ignoredWords, expectedAnagram));
  }

  @Test
  public void whenPhraseCanBeSolvedByMultipleWordMethod_thenIsReturned() {
    String phrase = "lleh lleh";
    List<String> ignoredWords = List.of("lleh", "lleh");
    String expectedAnagram = "hell hell";

    when(anagramDictionary.findOneWordAnagram(any(), anyList())).thenReturn("");
    when(anagramDictionary.findOneWordAnagram("ehll", ignoredWords)).thenReturn("hell");

    String result = anagramSolver.solveAnagram(phrase);

    assertEquals(expectedAnagram, result);
  }

  @Test
  public void whenPhraseIsEmpty_thenEmptyIsReturned() {
    String phrase = "";
    String normalizedPhrase = "";
    List<String> ignoredWords = List.of();
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(phrase, normalizedPhrase, ignoredWords, expectedAnagram));
  }

  @Test
  public void whenPhraseIsNull_thenEmptyIsReturned() {
    String phrase = null;
    String normalizedPhrase = "";
    List<String> ignoredWords = List.of();
    String expectedAnagram = "";

    assertEquals(expectedAnagram, testLogic(phrase, normalizedPhrase, ignoredWords, expectedAnagram));
  }

  private String testLogic(String phrase, String normalizedPhrase, List<String> ignoredWords, String expectedAnagram) {
    when(anagramDictionary.findOneWordAnagram(normalizedPhrase, ignoredWords)).thenReturn(expectedAnagram);

    return anagramSolver.solveAnagram(phrase);
  }

}