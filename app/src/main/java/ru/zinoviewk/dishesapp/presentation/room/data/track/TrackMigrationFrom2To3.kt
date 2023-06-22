package ru.zinoviewk.dishesapp.presentation.room.data.track

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class TrackMigrationFrom2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(renameAlbumIdToIdPrimaryKey())
        database.execSQL(addAlbumIdColumn())
    }

    private fun renameAlbumIdToIdPrimaryKey(): String {
        return "ALTER TABLE albums RENAME COLUMN albumId TO id"
    }

    private fun addAlbumIdColumn(): String {
        return "ALTER TABLE albums ADD COLUMN albumId INTEGER NOT NULL DEFAULT -1"
    }
}