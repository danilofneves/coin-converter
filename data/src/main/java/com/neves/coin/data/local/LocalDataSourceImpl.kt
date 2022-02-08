package com.neves.coin.data.local

import com.neves.coin.data.local.database.ExchangeDAO
import com.neves.coin.data.local.mapper.toExchange
import com.neves.coin.data.local.model.ExchangeRoom
import com.neves.coin.domain.Either
import com.neves.coin.domain.exception.ExchangeException
import com.neves.coin.domain.model.Exchange
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(private val dao: ExchangeDAO) : LocalDataSource {

    override suspend fun save(exchangeRoom: ExchangeRoom): Either<Unit, Exception> {
        return try{
            dao.insert(exchangeRoom)
            Either.Success(Unit)
        }
        catch (e:Exception){
            Either.Failure(ExchangeException.GeneralInsertException)
        }
    }

    override suspend fun getAll(): Either<List<Exchange>, Exception> {
        return try{
            val result = dao.getAll()
            Either.Success(result.map{
                it.toExchange()
            })
        }
        catch (e:Exception){
            Either.Failure(ExchangeException.GeneralInsertException)
        }
    }
}
