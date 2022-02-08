package com.neves.coin.data.repository

import com.neves.coin.data.local.LocalDataSource
import com.neves.coin.data.local.mapper.toExchangeRoom
import com.neves.coin.data.remote.RemoteDataSource
import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange
import com.neves.coin.domain.repository.ExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExchangeRepositoryImpl constructor (
    private val local: LocalDataSource,
    private val remote: RemoteDataSource):
    ExchangeRepository {

    override suspend fun exchangeValue(coin:String): Either<Exchange, Exception> {
        return withContext(Dispatchers.IO){
            remote.exchangeValue(coin)
        }
    }

    override suspend fun save(exchange: Exchange) : Either<Unit, Exception> {
        return withContext(Dispatchers.IO) {
            local.save(exchange.toExchangeRoom())
        }
    }

    override suspend fun getAll() : Either<List<Exchange>, Exception> {
        return withContext(Dispatchers.IO) {
            local.getAll()
        }
    }

}