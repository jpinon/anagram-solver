package com.jpinon.challenge.recruiting_javier_pinon.infrastructure.runner.cli;

import java.util.Scanner;

public class CliManager {

  private static final String PROMPT = "matchFor> ";
  private static final String SOLUTION_FOUND = "Longest anagram is... '";
  private static final String SOLUTION_NOT_FOUND = "Longest anagram is... not found here";

  private static final Scanner scanner = new Scanner(System.in);

  public static void print(String toPrint) {
    System.out.println(toPrint);
  }

  public static void printPrompt() {
    System.out.print(PROMPT);
  }

  public static void printSolution(String solution) {
    if (solution == null || solution.isBlank()) {
      print(SOLUTION_NOT_FOUND);
    } else {
      print(SOLUTION_FOUND + solution + "'");
    }
  }

  public static String readInput() {
    return scanner.nextLine();
  }

}
