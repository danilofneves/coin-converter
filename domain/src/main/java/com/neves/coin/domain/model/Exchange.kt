package com.neves.coin.domain.model

data class Exchange(
    var id: Long,
    val code: String,
    val codein: String,
    val name: String,
    val bid: Double
)