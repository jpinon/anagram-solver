package com.jpinon.challenge.recruiting_javier_pinon.application.word_management;

import java.util.List;
import java.util.Set;

public interface WordShuffler {

  Set<List<String>> getAllCombinations(List<Integer> schema, String word);
}
