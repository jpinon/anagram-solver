package com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file;

public class FilesCouldNotBeLoadedException extends RuntimeException {

  public FilesCouldNotBeLoadedException(String message) {
    super(message);
  }
}
