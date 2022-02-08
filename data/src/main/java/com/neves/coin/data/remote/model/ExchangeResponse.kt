package com.neves.coin.data.remote.model

typealias ExchangeResponseType = HashMap<String, ExchangeResponse>

data class ExchangeResponse (
        var id: Long,
        val code: String,
        val codein: String,
        val name: String,
        val bid: Double
    )
