package com.mower.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsoleApplicationShould {

  private static final int ANY_WIDTH = 5;
  private static final int ANY_HEIGHT = 5;
  private static final int ANY_INITIAL_COORDINATE_X = 1;
  private static final int ANY_INITIAL_COORDINATE_Y = 1;
  private static final int ANY_OTHER_INITIAL_COORDINATE_X = 1;
  private static final int ANY_OTHER_INITIAL_COORDINATE_Y = 0;
  private static final CardinalPoint ANY_CARDINAL_POINT = NORTH;
  private static final List<Command> ANY_MOWER_COMMANDS = List.of(LEFT, RIGHT, MOVE);
  private static final List<Command> TO_OUTSIDE_MOWER_COMMANDS = List.of(MOVE, MOVE, MOVE, MOVE, MOVE, MOVE, MOVE);
  private static final List<Command> NO_MOWER_COMMANDS = List.of();
  private static final List<Command> ANY_OTHER_MOWER_COMMANDS = List.of(MOVE);
  private static final boolean FINISHED_VALUE = true;
  private static final boolean NO_FINISHED_VALUE = false;

  @Mock
  CommandConsole commandConsole;
  private ConsoleApplication consoleApplication;

  @BeforeEach
  void setUp() {
    this.consoleApplication = new ConsoleApplication(commandConsole);
  }

  @Test
  void executeMowerCommandsInPlateau() {
    when(commandConsole.readPlateau()).thenReturn(anyPlateau());
    when(commandConsole.readMowerGiven(any(Plateau.class))).thenReturn(anyMower());
    when(commandConsole.readMowerCommands()).thenReturn(ANY_MOWER_COMMANDS);
    when(commandConsole.readIsFinished()).thenReturn(FINISHED_VALUE);
    consoleApplication.start();
    verify(commandConsole).printSituationOf(any(Mower.class));
  }

  @Test
  void executeMowerCommandsInPlateauThatGoOutsideOfThePlateau() {
    when(commandConsole.readPlateau()).thenReturn(anyPlateau());
    when(commandConsole.readMowerGiven(any(Plateau.class))).thenReturn(anyMower());
    when(commandConsole.readMowerCommands()).thenReturn(TO_OUTSIDE_MOWER_COMMANDS);
    when(commandConsole.readIsFinished()).thenReturn(FINISHED_VALUE);
    consoleApplication.start();
    verify(commandConsole).printErrorMessage(any(String.class));
  }

  @Test
  void executeMowerCommandsInPlateauWhereCrashesTwoMowers() {
    when(commandConsole.readPlateau()).thenReturn(anyPlateau());
    when(commandConsole.readMowerGiven(any(Plateau.class))).thenReturn(anyMower()).thenReturn(anyOtherMower());
    when(commandConsole.readMowerCommands()).thenReturn(NO_MOWER_COMMANDS).thenReturn(ANY_OTHER_MOWER_COMMANDS);
    when(commandConsole.readIsFinished()).thenReturn(NO_FINISHED_VALUE).thenReturn(FINISHED_VALUE);
    consoleApplication.start();
    verify(commandConsole, new Times(2)).printSituationOf(any(Mower.class));
    verify(commandConsole).printErrorMessage(any(String.class));
  }

  private Plateau anyPlateau() {
    return new Plateau(anyPlateauCoordinates());
  }

  private Coordinates anyPlateauCoordinates() {
    return new Coordinates(ANY_WIDTH, ANY_HEIGHT);
  }

  private Mower anyMower() {
    return new Mower(anyCoordinates(), anyFaceTo());
  }

  private Coordinates anyCoordinates() {
    return new Coordinates(ANY_INITIAL_COORDINATE_X, ANY_INITIAL_COORDINATE_Y);
  }

  private FaceTo anyFaceTo() {
    return new FaceTo(ANY_CARDINAL_POINT);
  }

  private Mower anyOtherMower() {
    return new Mower(anyOtherCoordinates(), anyFaceTo());
  }

  private Coordinates anyOtherCoordinates() {
    return new Coordinates(ANY_OTHER_INITIAL_COORDINATE_X, ANY_OTHER_INITIAL_COORDINATE_Y);
  }

}
