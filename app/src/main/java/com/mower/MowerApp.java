package com.mower;

import com.mower.application.CommandConsole;
import com.mower.application.ConsoleApplication;

import java.nio.charset.Charset;
import java.util.Scanner;

public class MowerApp {

  private static ConsoleApplication consoleApplication = new ConsoleApplication(systemInputCommandConsole());

  public static void main(String... args) {
    consoleApplication.start();
  }

  static void initForTestPurposes(ConsoleApplication testConsoleApplication) {
    consoleApplication = testConsoleApplication;
  }

  private static CommandConsole systemInputCommandConsole() {
    return new CommandConsole(new Scanner(System.in, Charset.defaultCharset()));
  }
}
