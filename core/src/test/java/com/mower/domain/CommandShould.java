package com.mower.domain;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandShould {

  private static final String UNKNOWN_COMMAND_CODE = "UNKNOWN COMMAND CODE";
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 1;
  private static final CardinalPoint CARDINAL_POINT = NORTH;

  @Test
  void throwUnknownCommandCodeIfCardinalPointCodeIsUnknown() {
    assertThrows(UnknownCommandCode.class, () -> Command.fromCode(UNKNOWN_COMMAND_CODE));
  }

  @Test
  void returnCommandLeftFromLCode() {
    assertSame(LEFT, Command.fromCode("L"));
  }

  @Test
  void returnCommandRightFromRCode() {
    assertSame(RIGHT, Command.fromCode("R"));
  }

  @Test
  void returnCommandMoveFromMCode() {
    assertSame(MOVE, Command.fromCode("M"));
  }

  @Test
  void executeLeftCommandAction() {
    LEFT.execute(mower());
  }

  @Test
  void executeRightCommandAction() {
    RIGHT.execute(mower());
  }

  @Test
  void executeMoveCommandAction() {
    MOVE.execute(mower());
  }

  private Mower mower() {
    return new Mower(coordinates(), faceTo());
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private FaceTo faceTo() {
    return new FaceTo(CARDINAL_POINT);
  }
}
