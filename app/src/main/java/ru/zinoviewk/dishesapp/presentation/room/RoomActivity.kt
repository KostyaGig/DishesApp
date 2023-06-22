package ru.zinoviewk.dishesapp.presentation.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.presentation.core.log
import ru.zinoviewk.dishesapp.presentation.room.data.MusicDatabase
import ru.zinoviewk.dishesapp.presentation.room.data.album.AlbumEntity
import ru.zinoviewk.dishesapp.presentation.room.data.artist.ArtistEntity
import ru.zinoviewk.dishesapp.presentation.room.data.track.TrackEntity
import ru.zinoviewk.dishesapp.presentation.room.data.track.TrackMetaData

class RoomActivity : AppCompatActivity() {

    private val tracksDao by lazy {
        MusicDatabase.getInstance(application).tracksDao()
    }

    private val albumsDao by lazy {
        MusicDatabase.getInstance(application).albumsDao()
    }

    private val artistsDao by lazy {
        MusicDatabase.getInstance(application).artistsDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        lifecycleScope.launch(Dispatchers.IO) {
            launch { generateTracks() }
            launch { generateAlbums() }
            launch { generateArtists() }
            launch {
                //log("Tracks with albums join ${tracksDao.getTracksWithAlbumsJoin()}")
            }
            //launch { log("First record is ${tracksDao.getFirstRecord()}") }
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                tracksDao.getTracks().onEach {
//                    log("Track entities $it")
//                }.launchIn(this)
//
//                tracksDao.getTracksTitlesByAlbumId(1)
//                    .onEach {
//                        log("Track titles by albumId $it")
//                    }.launchIn(this)
//                tracksDao
//                    .getAlbumTracks()
//                    .onEach {
//                        log(it.joinToString(separator = "\n"))
//                    }
//                    .launchIn(this)
                albumsDao
                    .getAlbumWithTracksAndArtists()
                    .onEach {
                        log("${it.joinToString(separator = "\n")}")
                    }.launchIn(this)
            }

        }

    }

    private suspend fun generateTracks() {
        var trackId = 0
        while (true) {
            delay(300)
            tracksDao.insertTrack(
                TrackEntity(
                    trackTitle = "Track $trackId",
                    artistName = "Artist $trackId",
                    albumId = trackId % 4,
                    id = trackId,
                    src = "Yandex",
                    metaData = TrackMetaData(
                        dob = "Today",
                        isExplicit = trackId % 2
                    ),
                    boolOne = true
                )
            )
            trackId++
        }
    }

    private suspend fun generateAlbums() {
        var albumId = 0
        while (true) {
            delay(100)
            albumsDao.insert(
                listOf(
                    AlbumEntity(
                        id = albumId,
                        albumId = albumId % 4,
                        title = "Album $albumId",
                        artist = "Artist $albumId"
                    )
                )
            )
            albumId++
        }
    }

    private suspend fun generateArtists() {
        var artistId = 0
        while (true) {
            delay(100)
            artistsDao.insert(
                ArtistEntity(
                    recordId = artistId,
                    artistId = artistId % 4,
                    albumId = artistId % 7,
                    title = "Artist $artistId",
                    trackId = artistId % 8,
                )
            )
            artistId++
        }
    }

}