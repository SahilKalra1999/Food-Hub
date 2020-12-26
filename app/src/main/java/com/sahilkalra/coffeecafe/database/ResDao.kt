package com.sahilkalra.coffeecafe.database

import androidx.room.*

@Dao
interface ResDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRes(resEntity: ResEntity)

    @Delete
    fun deleteRes(resEntity: ResEntity)

    @Query("Select * FROM restaurants")
    fun getAllRes(): List<ResEntity>

    @Query("Select * FROM restaurants WHERE res_name= :resName")
    fun getResByName(resName: String): ResEntity
}