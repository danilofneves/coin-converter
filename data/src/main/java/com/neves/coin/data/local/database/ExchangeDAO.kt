package com.neves.coin.data.local.database

import androidx.room.*
import com.neves.coin.data.local.model.ExchangeRoom

@Dao
interface ExchangeDAO {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(exchangeRoom: ExchangeRoom)

  @Query("SELECT * FROM tb_exchange")
  suspend fun getAll(): List<ExchangeRoom>

}
