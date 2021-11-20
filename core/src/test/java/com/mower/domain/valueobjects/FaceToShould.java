package com.mower.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.assertj.core.api.Assertions.assertThat;

class FaceToShould {
  @Test
  void returnNorthOrientation() {
    assertThat(faceToNorth().orientation()).isEqualTo(NORTH);
  }

  @Test
  void returnEastOrientation() {
    assertThat(faceToEast().orientation()).isEqualTo(EAST);
  }

  @Test
  void returnSouthOrientation() {
    assertThat(faceToSouth().orientation()).isEqualTo(SOUTH);
  }

  @Test
  void returnWestOrientation() {
    assertThat(faceToWest().orientation()).isEqualTo(WEST);
  }

  @Test
  void spinRightFromNorthToEast() {
    var faceToNorth = faceToNorth();
    faceToNorth.spinRight();
    assertThat(faceToNorth.orientation()).isEqualTo(faceToEast().orientation());
  }

  @Test
  void spinRightFromEastToSouth() {
    var faceToEast = faceToEast();
    faceToEast.spinRight();
    assertThat(faceToEast.orientation()).isEqualTo(faceToSouth().orientation());
  }

  @Test
  void spinRightFromSouthToWest() {
    var faceToSouth = faceToSouth();
    faceToSouth.spinRight();
    assertThat(faceToSouth.orientation()).isEqualTo(faceToWest().orientation());
  }

  @Test
  void spinRightFromWestToNorth() {
    var faceToWest = faceToWest();
    faceToWest.spinRight();
    assertThat(faceToWest.orientation()).isEqualTo(faceToNorth().orientation());
  }

  @Test
  void spinLeftFromNorthToWest() {
    var faceToNorth = faceToNorth();
    faceToNorth.spinLeft();
    assertThat(faceToNorth.orientation()).isEqualTo(faceToWest().orientation());
  }

  @Test
  void spinLeftFromEastToNorth() {
    var faceToEast = faceToEast();
    faceToEast.spinLeft();
    assertThat(faceToEast.orientation()).isEqualTo(faceToNorth().orientation());
  }

  @Test
  void spinLeftFromSouthToEast() {
    var faceToSouth = faceToSouth();
    faceToSouth.spinLeft();
    assertThat(faceToSouth.orientation()).isEqualTo(faceToEast().orientation());
  }

  @Test
  void spinLeftFromWestToSouth() {
    var faceToWest = faceToWest();
    faceToWest.spinLeft();
    assertThat(faceToWest.orientation()).isEqualTo(faceToSouth().orientation());
  }

  @Test
  void printFaceToNorthSituation() {
    assertThat(faceToNorth().situation()).isEqualTo(NORTH.code());
  }

  @Test
  void printFaceToEastSituation() {
    assertThat(faceToEast().situation()).isEqualTo(EAST.code());
  }

  @Test
  void printFaceToSouthSituation() {
    assertThat(faceToSouth().situation()).isEqualTo(SOUTH.code());
  }

  @Test
  void printFaceToWestSituation() {
    assertThat(faceToWest().situation()).isEqualTo(WEST.code());
  }

  private FaceTo faceToNorth() {
    return new FaceTo(NORTH);
  }

  private FaceTo faceToEast() {
    return new FaceTo(EAST);
  }

  private FaceTo faceToSouth() {
    return new FaceTo(SOUTH);
  }

  private FaceTo faceToWest() {
    return new FaceTo(WEST);
  }
}
