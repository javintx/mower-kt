package com.mower.domain.exception

class UnknownCardinalPointCode(code: String) : RuntimeException("Unknown cardinal point code $code")
