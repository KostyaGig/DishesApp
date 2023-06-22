package ru.zinoviewk.dishesapp.presentation.room.data.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
class AlbumEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("albumId")
    val albumId: Int,
    @ColumnInfo("albumTitle")
    val title: String,
    @ColumnInfo("artistName")
    val artist: String
) {
    override fun toString(): String {
        return title
    }
}