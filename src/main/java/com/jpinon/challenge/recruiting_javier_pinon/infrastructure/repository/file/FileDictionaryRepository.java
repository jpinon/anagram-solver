package com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jpinon.challenge.recruiting_javier_pinon.application.port.DictionaryRepository;

public class FileDictionaryRepository implements DictionaryRepository {

  private static final String EMPTY_PATH_EXCEPTION_MESSAGE = "Path is null or empty";
  private static final String PATH_NOT_FOUND_EXCEPTION_MESSAGE = "Path could not be found";
  private static final String FILE_ERROR_EXCEPTION_MESSAGE = "Error found parsing file: ";

  public List<String> getDictionaryWords(String path) {

    if (path == null || path.isBlank()) {
      throw new FilesCouldNotBeLoadedException(EMPTY_PATH_EXCEPTION_MESSAGE);
    }

    List<String> words = new ArrayList<>();

    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      paths.filter(Files::isRegularFile).forEach(pathToFile -> readFile(pathToFile, words));
    } catch (IOException e) {
      throw new FilesCouldNotBeLoadedException(PATH_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    return words;
  }

  private void readFile(Path path, List<String> words) {
    try (Stream<String> lines = Files.lines(path)) {
      words.addAll(lines.map(String::trim).collect(Collectors.toList()));
    } catch (IOException e) {
      throw new FilesCouldNotBeLoadedException(FILE_ERROR_EXCEPTION_MESSAGE + path);
    }
  }

}
