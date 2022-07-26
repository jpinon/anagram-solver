package com.jpinon.challenge.recruiting_javier_pinon.application.port;

public interface AnagramSolver {

  void initDictionary(String filesPath);

  String solveAnagram(String phrase);
}
