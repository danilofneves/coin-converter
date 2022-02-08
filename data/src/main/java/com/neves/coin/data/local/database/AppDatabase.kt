package com.neves.coin.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neves.coin.data.local.model.ExchangeRoom

@Database(entities = [ExchangeRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun taskDao(): ExchangeDAO

  companion object {
    private const val DATABASE = "coin_db"

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      val tempInstance =
        INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          DATABASE
        ).fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        return instance
      }
    }
  }
}
