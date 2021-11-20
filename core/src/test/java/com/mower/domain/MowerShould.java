package com.mower.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mower.domain.CardinalPoint.NORTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MowerShould {

  public static final String EXPECTED_MOWER_SITUATION = "1 2 N";
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;
  private static final CardinalPoint CARDINAL_POINT = NORTH;
  @Mock
  Command commandMocked;
  @Mock
  FaceTo faceToMocked;
  @Mock
  Coordinates coordinatesMocked;

  @Test
  void returnCoordinates() {
    assertEquals(anyCoordinates(), anyMower().coordinates());
  }

  @Test
  void executeCommand() {
    doNothing().when(commandMocked).execute(any(Mower.class));
    anyMower().executeCommand(commandMocked);
    verify(commandMocked).execute(any(Mower.class));
  }

  @Test
  void spinLeft() {
    doNothing().when(faceToMocked).spinLeft();
    anyMowerWithFaceToMocked().spinLeft();
    verify(faceToMocked).spinLeft();
  }

  @Test
  void spinRight() {
    doNothing().when(faceToMocked).spinRight();
    anyMowerWithFaceToMocked().spinRight();
    verify(faceToMocked).spinRight();
  }

  @Test
  void moveForward() {
    doNothing().when(coordinatesMocked).moveTowards(any(CardinalPoint.class));
    anyMowerWithCoordinatesMocked().moveForward();
    verify(coordinatesMocked).moveTowards(any(CardinalPoint.class));
  }

  @Test
  void returnSituation() {
    assertThat(anyMower().situation()).isEqualTo(EXPECTED_MOWER_SITUATION);
  }

  private Mower anyMower() {
    return new Mower(anyCoordinates(), anyFaceTo());
  }

  private Mower anyMowerWithFaceToMocked() {
    return new Mower(anyCoordinates(), faceToMocked);
  }

  private Mower anyMowerWithCoordinatesMocked() {
    return new Mower(coordinatesMocked, anyFaceTo());
  }

  private Coordinates anyCoordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private FaceTo anyFaceTo() {
    return new FaceTo(CARDINAL_POINT);
  }
}
