package com.jpinon.challenge.recruiting_javier_pinon.application.port;

import java.util.List;

public interface DictionaryRepository {

  List<String> getDictionaryWords(String path);
}
