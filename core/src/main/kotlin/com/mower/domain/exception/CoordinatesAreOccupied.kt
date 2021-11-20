package com.mower.domain.exception

class CoordinatesAreOccupied(situation: String) :
    RuntimeException("The coordinates ($situation) are occupied in the plateau")
