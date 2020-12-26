package com.sahilkalra.coffeecafe.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResEntity::class],version = 2)
abstract class RestaurantDatabase: RoomDatabase() {
    abstract fun resDao(): ResDao
}