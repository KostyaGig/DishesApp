package ru.zinoviewk.dishesapp.presentation.room.data.track

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class TrackMigrationFrom3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(addDOBColumn())
        database.execSQL(addIsExplicitColumn())
    }

    private fun addDOBColumn(): String {
        return "ALTER TABLE track_table ADD COLUMN 'meta_dob' TEXT"
    }

    private fun addIsExplicitColumn(): String {
        return "ALTER TABLE track_table ADD COLUMN 'meta_isExplicit' BOOLEAN"
    }
}