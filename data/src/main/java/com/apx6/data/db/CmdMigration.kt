package com.apx6.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_TO_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("")

            /* example*/
//            execSQL(
//                "CREATE TABLE IF NOT EXISTS approveLine (id INTEGER PRIMARY KEY NOT NULL, " +
//                    "docId INTEGER NOT NULL, name TEXT NOT NULL, department TEXT NOT NULL, position TEXT NOT NULL, status TEXT NOT NULL," +
//                    "profileImage TEXT NOT NULL, approveDate TEXT NOT NULL, ordering INTEGER NOT NULL )"
//            )
//
//            execSQL("ALTER TABLE workRecord ADD overWorkStartTime INTEGER NOT NULL DEFAULT 0")

//            execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_workRecord_employee_date ON workRecord(employee, date)")
        }
    }
}