package ru.zinoviewk.dishesapp.presentation.room.data.artist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ArtistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artistEntity: ArtistEntity)
}