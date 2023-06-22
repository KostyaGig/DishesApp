package ru.zinoviewk.dishesapp.presentation.room.data.album

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<AlbumEntity>)

    @Query("SELECT * FROM album_view")
    fun getAlbumWithTracksAndArtists(): Flow<List<AlbumWithTracksAndArtistView>>
}