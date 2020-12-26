package com.sahilkalra.coffeecafe.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class ResEntity(
    @PrimaryKey @ColumnInfo(name = "id")val id:String,
    @ColumnInfo(name = "res_name") val resName: String,
    @ColumnInfo(name = "res_rating") val resRating:String,
    @ColumnInfo(name = "res_cost") val resCost:String,
    @ColumnInfo(name = "res_image") val resImage: String,
    @ColumnInfo(name = "res_is_selected") val is_selected: Boolean=false
)
