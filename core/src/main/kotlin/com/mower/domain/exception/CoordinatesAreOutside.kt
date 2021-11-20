package com.mower.domain.exception

class CoordinatesAreOutside(coordinateX: Int, coordinateY: Int) :
    RuntimeException("The coordinates ($coordinateX, $coordinateY) are outside")
