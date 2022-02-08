package com.neves.coin.domain.usecases

import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange
import com.neves.coin.domain.repository.ExchangeRepository
import javax.inject.Inject

class ExchangeValue @Inject constructor(
    private val repository: ExchangeRepository
) {

    suspend operator fun invoke(coin:String): Either<Exchange, Exception> = repository.exchangeValue(coin)
}