package ru.zinoviewk.dishesapp.presentation.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.zinoviewk.dishesapp.presentation.room.data.album.AlbumEntity
import ru.zinoviewk.dishesapp.presentation.room.data.album.AlbumWithTracksAndArtistView
import ru.zinoviewk.dishesapp.presentation.room.data.album.AlbumsDao
import ru.zinoviewk.dishesapp.presentation.room.data.artist.ArtistsDao
import ru.zinoviewk.dishesapp.presentation.room.data.artist.ArtistEntity
import ru.zinoviewk.dishesapp.presentation.room.data.track.*

private const val DB_VERSION = 1

@Database(
    entities = [TrackEntity::class, AlbumEntity::class, ArtistEntity::class],
    version = DB_VERSION,
    exportSchema = true,
    views = [AlbumWithTracksAndArtistView::class]
)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun tracksDao(): TracksDao

    abstract fun albumsDao(): AlbumsDao

    abstract fun artistsDao(): ArtistsDao

    companion object {
        @Volatile
        private var instance: MusicDatabase? = null

        fun getInstance(context: Context): MusicDatabase {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            MusicDatabase::class.java,
                            "Musicdb"
                        )
                            .addMigrations(
                                TrackMigrationFrom1To2(),
                                TrackMigrationFrom2To3(),
                            )
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}