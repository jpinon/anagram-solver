package com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class FileDictionaryRepositoryTest {

  private FileDictionaryRepository repository = new FileDictionaryRepository();

  @Test
  public void whenThereIsOneFileOnDirectory_thenWordsAreReturned() {
    String path = "src/test/resources/com/jpinon/challenge/recruiting_javier_pinon"
        + "/infrastructure/repository/file/one_file";
    List<String> expectedWords = List.of("Hello", "World");

    List<String> resultWords = repository.getDictionaryWords(path);

    assertEquals(expectedWords.size(), resultWords.size());
    assertTrue(resultWords.containsAll(expectedWords));
  }

  @Test
  public void whenThereAreMultipleFilesOnDirectory_thenWordsAreReturned() {
    String path = "src/test/resources/com/jpinon/challenge/recruiting_javier_pinon"
        + "/infrastructure/repository/file/multiple_files";
    List<String> expectedWords = List.of("Hello", "World", "Foo", "Bar");

    List<String> resultWords = repository.getDictionaryWords(path);

    assertEquals(expectedWords.size(), resultWords.size());
    assertTrue(resultWords.containsAll(expectedWords));
  }

  @Test(expected = FilesCouldNotBeLoadedException.class)
  public void whenDirectoryDoesNotExist_thenExceptionIsThrown() {
    String path = "/not/an/existing/directory";

    repository.getDictionaryWords(path);
  }

  @Test(expected = FilesCouldNotBeLoadedException.class)
  public void whenPathIsEmpty_thenExceptionIsThrown() {
    String path = "";

    repository.getDictionaryWords(path);
  }

  @Test(expected = FilesCouldNotBeLoadedException.class)
  public void whenPathIsNull_thenExceptionIsThrown() {
    String path = null;

    repository.getDictionaryWords(path);
  }
}