package com.mower.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Scanner;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandConsoleShould {

  private static final int ANY_COORDINATE_X = 1;
  private static final int ANY_COORDINATE_Y = 2;
  private static final CardinalPoint ANY_CARDINAL_POINT = NORTH;
  private static final String VALID_INPUT_PLATEAU_DIMENSIONS = "5 5";
  private static final String INVALID_INPUT_PLATEAU_DIMENSIONS = "INVALID DIMENSIONS";
  private static final int PLATEAU_WIDTH = 5;
  private static final int PLATEAU_HEIGHT = 5;
  private static final String INPUT_MOWER_DEFINITION_OUTSIDE_PLATEAU = "6 6 N";
  private static final String INPUT_MOWER_DEFINITION_IN_OCCUPIED_COORDINATE = "3 3 N";
  private static final String VALID_INPUT_MOWER_DEFINITION = "1 2 N";
  private static final int VALID_MOWER_COORDINATE_X = 1;
  private static final int VALID_MOWER_COORDINATE_Y = 2;
  private static final int OCCUPIED_COORDINATE_X = 3;
  private static final int OCCUPIED_COORDINATE_Y = 3;
  private static final String INVALID_INPUT_MOWER_COMMANDS = "INVALID MOWER COMMANDS";
  private static final String VALID_INPUT_MOWER_COMMANDS = "LRM";
  private static final List<Command> EXPECTED_MOWER_COMMANDS = List.of(LEFT, RIGHT, MOVE);
  private static final String VALID_INPUT_IS_FINISHED = "y";
  private static final String VALID_INPUT_IS_NOT_FINISHED = "N";
  private static final String INVALID_INPUT_IS_FINISHED = "INVALID";
  private static final String ANY_ERROR_MESSAGE = "ANY ERROR MESSAGE";
  private static final String ANY_MESSAGE = "ANY MESSAGE";

  @Mock
  Scanner scannerMocked;
  private CommandConsole commandConsole;

  @BeforeEach
  void setUp() {
    commandConsole = new CommandConsole(scannerMocked);
  }

  @Test
  void readPlateau() {
    when(scannerMocked.nextLine()).thenReturn(INVALID_INPUT_PLATEAU_DIMENSIONS, VALID_INPUT_PLATEAU_DIMENSIONS);
    assertThat(commandConsole.readPlateau()).isNotNull();
    verify(scannerMocked, new Times(2)).nextLine();
  }

  @Test
  void readMower() {
    when(scannerMocked.nextLine())
        .thenReturn(INPUT_MOWER_DEFINITION_OUTSIDE_PLATEAU)
        .thenReturn(INPUT_MOWER_DEFINITION_IN_OCCUPIED_COORDINATE)
        .thenReturn(VALID_INPUT_MOWER_DEFINITION);
    assertThat(commandConsole.readMowerGiven(plateauWithOccupiedCoordinates()).coordinates()).isEqualTo(validMowerCoordinates());
    verify(scannerMocked, new Times(3)).nextLine();
  }

  @Test
  void readMowerCommands() {
    when(scannerMocked.nextLine()).thenReturn(INVALID_INPUT_MOWER_COMMANDS, VALID_INPUT_MOWER_COMMANDS);
    assertThat(commandConsole.readMowerCommands()).isEqualTo(expectedMowerCommands());
    verify(scannerMocked, new Times(2)).nextLine();
  }

  @Test
  void readIsFinished() {
    when(scannerMocked.nextLine()).thenReturn(INVALID_INPUT_IS_FINISHED, VALID_INPUT_IS_FINISHED);
    assertThat(commandConsole.readIsFinished()).isTrue();
    verify(scannerMocked, new Times(2)).nextLine();
  }

  @Test
  void readIsNotFinished() {
    when(scannerMocked.nextLine()).thenReturn(VALID_INPUT_IS_NOT_FINISHED);
    assertThat(commandConsole.readIsFinished()).isFalse();
    verify(scannerMocked, new Times(1)).nextLine();
  }

  @Test
  void printSituationOfMower() {
    commandConsole.printSituationOf(anyMower());
  }

  @Test
  void printMessage() {
    commandConsole.printMessage(ANY_MESSAGE);
  }

  @Test
  void printErrorMessage() {
    commandConsole.printErrorMessage(ANY_ERROR_MESSAGE);
  }

  private Mower anyMower() {
    return new Mower(anyCoordinates(), anyFaceTo());
  }

  private Coordinates anyCoordinates() {
    return new Coordinates(ANY_COORDINATE_X, ANY_COORDINATE_Y);
  }

  private FaceTo anyFaceTo() {
    return new FaceTo(ANY_CARDINAL_POINT);
  }

  private Plateau plateauWithOccupiedCoordinates() {
    var plateau = new Plateau(plateauCoordinates());
    plateau.occupyCoordinate(occupiedCoordinates());
    return plateau;
  }

  private Coordinates plateauCoordinates() {
    return new Coordinates(PLATEAU_WIDTH, PLATEAU_HEIGHT);
  }

  private Coordinates occupiedCoordinates() {
    return new Coordinates(OCCUPIED_COORDINATE_X, OCCUPIED_COORDINATE_Y);
  }

  private Coordinates validMowerCoordinates() {
    return new Coordinates(VALID_MOWER_COORDINATE_X, VALID_MOWER_COORDINATE_Y);
  }

  private List<Command> expectedMowerCommands() {
    return EXPECTED_MOWER_COMMANDS;
  }

}
