package ru.zinoviewk.dishesapp.presentation.room.data.track

import androidx.room.*
import androidx.work.impl.utils.INITIAL_ID

@Entity(tableName = "track_table")
class TrackEntity(
    @ColumnInfo(name = "title")
    val trackTitle: String,
    @ColumnInfo(name = "artist")
    val artistName: String,
    @ColumnInfo(name = "trackAlbumId")
    val albumId: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("trackId")
    val id: Int,
    @ColumnInfo("duration")
    val duration: Long = 0L,
    @ColumnInfo("src")
    val src: String,
    @Embedded(prefix = "meta_")
    val metaData: TrackMetaData,
    @ColumnInfo("boolOne")
    val boolOne: Boolean,
) {
    override fun toString(): String {
        return trackTitle
    }
}

class TrackMetaData(
    @ColumnInfo("dob")
    val dob: String,
    @ColumnInfo("isExplicit")
    val isExplicit: Int = 0
)