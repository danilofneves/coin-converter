package com.neves.coin.domain.exception

sealed class ExchangeException () : Exception(){

    object GeneralInsertException: ExchangeException()
    object GeneralListException: ExchangeException()
}