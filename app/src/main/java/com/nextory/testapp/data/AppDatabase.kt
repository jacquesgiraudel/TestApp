package com.nextory.testapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        Book::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE book ADD COLUMN favorite INTEGER NOT NULL DEFAULT(0)")
    }
}