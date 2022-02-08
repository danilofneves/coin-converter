package com.neves.coin.data.remote.mapper

import com.neves.coin.data.remote.model.ExchangeResponse
import com.neves.coin.domain.model.Exchange

fun ExchangeResponse.toExchange(): Exchange {
    return Exchange(
        id = id,
        code = code,
        codein = codein,
        name = name,
        bid = bid
    )
}