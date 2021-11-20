package com.mower.domain.exception

class CoordinatesMustBePositiveNumbers(coordinateX: Int, coordinateY: Int) :
    RuntimeException("Invalid coordinates ($coordinateX, $coordinateY). They should be positive")
