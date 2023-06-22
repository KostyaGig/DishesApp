package ru.zinoviewk.dishesapp.presentation.room.data.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
class ArtistEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val recordId: Int,
    @ColumnInfo("artistId")
    val artistId: Int,
    @ColumnInfo("artistTitle")
    val title: String,
    @ColumnInfo("artistAlbumId")
    val albumId: Int,
    @ColumnInfo("artistTrackId")
    val trackId: Int
) {

    override fun toString(): String {
        return title
    }
}