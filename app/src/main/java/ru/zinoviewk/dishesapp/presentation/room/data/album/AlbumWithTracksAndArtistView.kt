package ru.zinoviewk.dishesapp.presentation.room.data.album

import androidx.room.*
import ru.zinoviewk.dishesapp.presentation.room.data.artist.ArtistEntity
import ru.zinoviewk.dishesapp.presentation.room.data.track.TrackEntity

@DatabaseView(
    """
        SELECT albums.albumId FROM albums
    """,
    viewName = "album_view"
)
class AlbumWithTracksAndArtistView(
    @ColumnInfo("albumId")
    val albumId: Int,

    @Relation(
        parentColumn = "albumId",
        entityColumn = "trackAlbumId"
    )
    val tracks: List<TrackEntity>,

    @Relation(
        parentColumn = "albumId",
        entityColumn = "artistAlbumId"
    )
    val artists: List<ArtistEntity>
) {
    override fun toString(): String {
        return "$albumId, tracks: ${tracks.map { it.trackTitle }}, artists ${artists.map { it.title }}"
    }
}
