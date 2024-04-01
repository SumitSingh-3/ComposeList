package com.example.myapplication.network.models

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)