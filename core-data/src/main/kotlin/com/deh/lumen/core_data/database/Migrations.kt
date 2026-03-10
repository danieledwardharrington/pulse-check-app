package com.deh.lumen.core_data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // SQL to migrate schema from version 1 to version 2
        }
    }

    val ALL = arrayOf(
        MIGRATION_1_2
    )
}