package com.example.zameen_mad

data class OpenCageResponse(
    val results: List<Result>
)

data class Result(
    val formatted: String
)