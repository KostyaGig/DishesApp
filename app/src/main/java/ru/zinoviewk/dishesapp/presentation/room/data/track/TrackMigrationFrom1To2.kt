package ru.zinoviewk.dishesapp.presentation.room.data.track

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class TrackMigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(createAlbumsTable())
    }

    private fun createAlbumsTable(): String {
        return "CREATE TABLE albums(albumId INT PRIMARY KEY NOT NULL, albumTitle TEXT NOT NULL, artistName TEXT NOT NULL)"
    }
}