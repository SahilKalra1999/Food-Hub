package com.sahilkalra.coffeecafe.model

data class Restaurant(
    val id: String,
    val resName: String,
    val resRating: String,
    val resCost: String,
    val resImage: String,
    var is_selected: Boolean = false
)