package ru.zinoviewk.dishesapp.presentation.room.data.track

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.zinoviewk.dishesapp.presentation.room.data.album.AlbumEntity

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(trackEntity: TrackEntity)

    @Query("SELECT * FROM track_table")
    fun getTracks(): Flow<List<TrackEntity>>

    @Query("SELECT title FROM track_table WHERE trackAlbumId LIKE :albumId")
    fun getTracksTitlesByAlbumId(albumId: Int): Flow<List<String>>

    @Query("SELECT rowId FROM track_table ORDER BY rowId ASC LIMIT 1")
    fun getFirstRecord(): Int?

    @Query(
        "SELECT track.trackId AS trackId, " +
        "track.title AS trackTitle, " +
        "album.albumTitle AS albumTitle, " +
        "album.artistName AS artistName " +
        "FROM track_table AS track, albums AS album " +
        "WHERE track.trackAlbumId == album.albumId;"
    )
    fun getTracksWithAlbums(): List<TrackWithAlbum>

    @Query(
        """SELECT track.trackId AS trackId,
            track.title AS trackTitle,
            album.albumTitle AS albumTitle,
            album.artistName AS artistName
            FROM track_table AS track, albums AS album
            WHERE track.trackAlbumId == album.albumId;
        """
    )
    fun getTracksWithAlbums2(): List<TrackWithAlbum>

    @Query(
        """
            SELECT track.trackId AS trackId,
            track.title AS trackTitle,
            album.albumTitle AS albumTitle,
            album.artistName AS artistName
            FROM track_table AS track
            INNER JOIN albums AS album ON track.trackAlbumId == album.albumId;
        """
    )
    fun getTracksWithAlbumsJoin(): List<TrackWithAlbum>

    @Transaction
    @Query("SELECT * FROM albums")
    fun getAlbumTracks(): Flow<List<TracksOfAlbum>>
}

class TrackWithAlbum(
    @ColumnInfo("trackId")
    val trackId: Int,
    @ColumnInfo("trackTitle")
    val trackTitle: String,
    @ColumnInfo("albumTitle")
    val albumTitle: String,
    @ColumnInfo("artistName")
    val artistName: String
) {

}


class AlbumsOfTrack(
    @ColumnInfo(name = "title")
    val trackTitle: String,
    @Embedded(prefix = "meta_")
    val metaData: TrackMetaData,
    @Relation(
        parentColumn = "trackAlbumId",
        entityColumn = "albumId"
    )
    val albums: List<AlbumEntity>
)

class TracksOfAlbum(
    @ColumnInfo("albumId")
    val albumId: Int,
    @ColumnInfo("albumTitle")
    val title: String,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "trackAlbumId"
    )
    val tracks: List<TrackEntity>
) {
    override fun toString(): String {
        return "$albumId: $tracks"
    }
}