package com.neves.coin.presentation.ui.history.state


data class ExchangeItemUiState (
    val id: Long,
    val code: String,
    val codein: String,
    val name: String,
    val bid: Double
)