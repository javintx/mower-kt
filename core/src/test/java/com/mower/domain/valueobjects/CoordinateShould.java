package com.mower.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateShould {

  public static final String EXPECTED_COORDINATES_SITUATION = "1 2";
  private static final int COORDINATE_ZERO = 0;
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;
  private static final int OTHER_COORDINATE_X = 3;
  private static final int OTHER_COORDINATE_Y = 4;
  private static final int LESS_THAN_ZERO_DIMENSION = -1;

  @Test
  void throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateXZeroOrLess() {
    assertThrows(CoordinatesMustBePositiveNumbers.class,
        () -> new Coordinates(LESS_THAN_ZERO_DIMENSION, COORDINATE_Y));
  }

  @Test
  void throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateYZeroOrLess() {
    assertThrows(CoordinatesMustBePositiveNumbers.class,
        () -> new Coordinates(COORDINATE_X, LESS_THAN_ZERO_DIMENSION));
  }

  @Test
  void printSituation() {
    assertThat(coordinates().situation()).isEqualTo(EXPECTED_COORDINATES_SITUATION);
  }

  @Test
  void verifyInsideCoordinates() {
    coordinatesInside().verifyAreInside(zeroCoordinates(), otherCoordinates());
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByHeight() {
    assertThrows(CoordinatesAreOutside.class,
        () -> otherCoordinatesAboveByHeight().verifyAreInside(zeroCoordinates(), otherCoordinates()));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByWidth() {
    assertThrows(CoordinatesAreOutside.class,
        () -> otherCoordinatesAboveByWidth().verifyAreInside(zeroCoordinates(), otherCoordinates()));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByHeight() {
    assertThrows(CoordinatesAreOutside.class,
        () -> coordinatesBelowByHeight().verifyAreInside(coordinates(), otherCoordinates()));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByWidth() {
    assertThrows(CoordinatesAreOutside.class,
        () -> coordinatesBelowByWidth().verifyAreInside(coordinates(), otherCoordinates()));
  }

  @Test
  void moveTowardsNorth() {
    var coordinates = coordinates();
    coordinates.moveTowards(NORTH);
    assertThat(coordinates).isEqualTo(coordinatesMovedToNorth());
  }

  @Test
  void moveTowardsEast() {
    var coordinates = coordinates();
    coordinates.moveTowards(EAST);
    assertThat(coordinates).isEqualTo(coordinatesMovedToEast());
  }

  @Test
  void moveTowardsSouth() {
    var coordinates = coordinates();
    coordinates.moveTowards(SOUTH);
    assertThat(coordinates).isEqualTo(coordinatesMovedToSouth());
  }

  @Test
  void moveTowardsWest() {
    var coordinates = coordinates();
    coordinates.moveTowards(WEST);
    assertThat(coordinates).isEqualTo(coordinatesMovedToWest());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesAreEquals() {
    assertThat(coordinates()).isEqualTo(coordinates());
  }

  @Test
  void ensureSameCoordinatesIsEqualsToItself() {
    var coordinates = coordinates();
    // assertThat(coordinates).isEqualsTo(coordinates) does not works for this case
    assertEquals(coordinates, coordinates);
  }

  @Test
  void ensureOtherClassIsNotEqualsToCoordinates() {
    assertNotEquals(coordinates(), "String class");
  }

  @Test
  void ensureNullIsNotEqualsToCoordinates() {
    assertNotEquals(coordinates(), null);
  }

  @Test
  void ensureTwoCoordinatesWithDifferentCoordinateXAreNotEquals() {
    assertNotEquals(coordinates(), coordinatesAboveByWidth());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentCoordinateYAreNotEquals() {
    assertNotEquals(coordinates(), coordinatesAboveByHeight());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesHaveSameHashCode() {
    assertThat(coordinates().hashCode()).isEqualTo(coordinates().hashCode());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesHaveDifferentHashCode() {
    assertThat(coordinates().hashCode()).isNotEqualTo(otherCoordinates().hashCode());
  }

  private Coordinates zeroCoordinates() {
    return new Coordinates(COORDINATE_ZERO, COORDINATE_ZERO);
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates coordinatesInside() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates otherCoordinatesAboveByHeight() {
    return new Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y + 1);
  }

  private Coordinates otherCoordinatesAboveByWidth() {
    return new Coordinates(OTHER_COORDINATE_X + 1, OTHER_COORDINATE_Y);
  }

  private Coordinates coordinatesAboveByHeight() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private Coordinates coordinatesAboveByWidth() {
    return new Coordinates(COORDINATE_X + 1, COORDINATE_Y);
  }

  private Coordinates coordinatesBelowByHeight() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y - 1);
  }

  private Coordinates coordinatesBelowByWidth() {
    return new Coordinates(COORDINATE_X - 1, COORDINATE_Y);
  }

  private Coordinates otherCoordinates() {
    return new Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToNorth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private Coordinates coordinatesMovedToEast() {
    return new Coordinates(COORDINATE_X + 1, COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToSouth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y - 1);
  }

  private Coordinates coordinatesMovedToWest() {
    return new Coordinates(COORDINATE_X - 1, COORDINATE_Y);
  }
}
