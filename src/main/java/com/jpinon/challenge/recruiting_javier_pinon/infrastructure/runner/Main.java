package com.jpinon.challenge.recruiting_javier_pinon.infrastructure.runner;

import com.jpinon.challenge.recruiting_javier_pinon.application.adapter.AnagramSolverImpl;
import com.jpinon.challenge.recruiting_javier_pinon.application.port.AnagramSolver;
import com.jpinon.challenge.recruiting_javier_pinon.application.port.DictionaryRepository;
import com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file.FileDictionaryRepository;
import com.jpinon.challenge.recruiting_javier_pinon.infrastructure.repository.file.FilesCouldNotBeLoadedException;
import com.jpinon.challenge.recruiting_javier_pinon.infrastructure.runner.cli.CliManager;

public class Main {

  private static final String RUN_PARAM_ERROR =
      "Only 1 parameter describing the path to directory with text files must "
          + "be included when running this application";
  private static final String EXIT_KEYWORD = "/exit";

  private static final DictionaryRepository repository = new FileDictionaryRepository();
  private static final AnagramSolver anagramSolver = new AnagramSolverImpl(repository);

  public static void main(String[] args) {

    if (args.length != 1) {
      CliManager.print(RUN_PARAM_ERROR);
      return;
    }
    try {
      anagramSolver.initDictionary(args[0]);
    } catch (FilesCouldNotBeLoadedException e) {
      CliManager.print(e.getMessage());
      return;
    }

    run();
  }

  private static void run() {
    CliManager.printPrompt();
    String userInput = CliManager.readInput();

    while (!isExitCommand(userInput)) {
      String solution = anagramSolver.solveAnagram(userInput);

      CliManager.printSolution(solution);

      CliManager.printPrompt();
      userInput = CliManager.readInput();
    }
  }

  private static boolean isExitCommand(String word) {
    return word.equals(EXIT_KEYWORD);
  }
}
