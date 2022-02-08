package com.neves.coin.presentation.ui.history.mapper

import com.neves.coin.domain.model.Exchange
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState

fun ExchangeItemUiState.toExchange() =
    Exchange(
        id,
        code,
        codein,
        name,
        bid
    )

fun Exchange.toExchangeItemUiState() =
    ExchangeItemUiState(
        id,
        code,
        codein,
        name,
        bid
    )