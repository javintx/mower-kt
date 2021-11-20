package com.mower.domain;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardinalPointShould {

  private static final String UNKNOWN_CARDINAL_POINT_CODE = "UNKNOWN CARDINAL POINT CODE";

  @Test
  void throwUnknownCardinalPointCodeIfCardinalPointCodeIsUnknown() {
    assertThrows(UnknownCardinalPointCode.class, () -> CardinalPoint.fromCode(UNKNOWN_CARDINAL_POINT_CODE));
  }

  @Test
  void returnNorthFromNCode() {
    assertSame(NORTH, CardinalPoint.fromCode("N"));
  }

  @Test
  void returnEastFromECode() {
    assertSame(EAST, CardinalPoint.fromCode("E"));
  }

  @Test
  void returnSouthFromSCode() {
    assertSame(SOUTH, CardinalPoint.fromCode("S"));
  }

  @Test
  void returnWestFromWCode() {
    assertSame(WEST, CardinalPoint.fromCode("W"));
  }

  @Test
  void returnNorthCode() {
    assertSame("N", NORTH.code());
  }

  @Test
  void returnEastCode() {
    assertSame("E", EAST.code());
  }

  @Test
  void returnSouthCode() {
    assertSame("S", SOUTH.code());
  }

  @Test
  void returnWestCode() {
    assertSame("W", WEST.code());
  }

}
