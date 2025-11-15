package com.oneapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {
        const val DATABASE_NAME = "oneapp_database"
    }
}

// Migration placeholder
// TODO: Add actual migrations as database schema evolves
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example migration: Add new column
        // database.execSQL("ALTER TABLE locations ADD COLUMN new_column TEXT")
    }
}

