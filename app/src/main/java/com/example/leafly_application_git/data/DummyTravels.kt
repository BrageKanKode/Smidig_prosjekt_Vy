package com.example.leafly_application_git.data

data class Location(
    val trip: List<Trip>
)

data class Trip(
    val id: Long,
    val fronLocation: String,
    val toLocation: String,
    val price: Int,
    val miljopoeng: Int
)