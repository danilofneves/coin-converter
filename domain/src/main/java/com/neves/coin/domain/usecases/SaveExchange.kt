package com.neves.coin.domain.usecases

import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange
import com.neves.coin.domain.repository.ExchangeRepository
import javax.inject.Inject

class SaveExchange @Inject constructor(
    private val repository: ExchangeRepository
) {

    suspend operator fun invoke(exchange: Exchange): Either<Unit, Exception> {
        return repository.save(exchange)
    }
}