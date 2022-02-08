package com.neves.coin.data.local.mapper

import com.neves.coin.data.local.model.ExchangeRoom
import com.neves.coin.domain.model.Exchange

fun Exchange.toExchangeRoom() =
    ExchangeRoom(
        id,
        code,
        codein,
        name,
        bid
    )

fun ExchangeRoom.toExchange(): Exchange {
    return Exchange(
        id = id,
        code = code,
        codein = codein,
        name = name,
        bid = bid
    )
}