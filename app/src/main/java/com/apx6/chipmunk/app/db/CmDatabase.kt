package com.apx6.chipmunk.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apx6.chipmunk.app.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


class CmDataBase() {}
//@Database(
//    entities = [
////        AccountEntity::class,
//    ],
//    version = 1,
//    exportSchema = false
//)
//
//abstract class CmDatabase : RoomDatabase() {
//
////    abstract fun accountDao(): AccountDao
//
//    class Callback @Inject constructor(
//        private val database: Provider<CmDatabase>,
//        @ApplicationScope private val applicationScope: CoroutineScope
//    ) : RoomDatabase.Callback()
//}