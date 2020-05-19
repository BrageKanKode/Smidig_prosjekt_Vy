package com.example.leafly_application_git.data


data class Features (

    val id : Int,
    val fronLocation : String,
    val toLocation : String,
    val price : String,
    val miljoPoeng : String
)

data class Json (

    val type : String,
    val features : List<Features>
)

data class locationId(
    val location: Features
)
