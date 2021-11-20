package com.mower.domain.exception

class UnknownCommandCode(code: String) : RuntimeException("Unknown command code $code")
