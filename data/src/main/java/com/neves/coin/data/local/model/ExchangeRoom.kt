package com.neves.coin.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_exchange")
data class ExchangeRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val code: String,
    val codein: String,
    val name: String,
    val bid: Double
)