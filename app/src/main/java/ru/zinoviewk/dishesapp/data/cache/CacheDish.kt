package ru.zinoviewk.dishesapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dishes")
data class CacheDish(
    @PrimaryKey @ColumnInfo("dishId") val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("price") val price: Int
)