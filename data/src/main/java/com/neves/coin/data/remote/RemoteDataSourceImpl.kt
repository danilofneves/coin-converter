package com.neves.coin.data.remote

import com.neves.coin.data.remote.mapper.toExchange
import com.neves.coin.data.remote.service.ExchangeService
import com.neves.coin.domain.Either
import com.neves.coin.domain.exception.ExchangeException
import com.neves.coin.domain.model.Exchange
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val exchangeService: ExchangeService) : RemoteDataSource {

    override suspend fun exchangeValue(coin:String): Either<Exchange, Exception> {
        return try{
            val result = exchangeService.exchangeValue(coin)
            Either.Success(result.values.first().toExchange())
        }
        catch (e:Exception){
            val s = e.message
            Either.Failure(ExchangeException.GeneralInsertException)
        }
    }
}
