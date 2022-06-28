package com.apx6.chipmunk.data.dao

import androidx.room.*


@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun overwrite(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun overwrite(obj: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: List<T>): List<Long>

    @Update
    abstract fun update(obj: T): Int

    @Update
    abstract fun update(obj: List<T>)

    @Delete
    abstract fun delete(obj: T): Int

    @Delete
    abstract fun delete(obj: List<T>)

    @Transaction
    open fun insertOrUpdate(obj: T): Int {
        val id = insert(obj)
        return if (id == -1L) update(obj) else id.toInt()
    }

    @Transaction
    open fun insertOrUpdate(objList: List<T>, purgeList: List<T>?= null) {
        if (objList.isNotEmpty()) {
            val insertResult = insert(objList)
            val updateList = mutableListOf<T>()

            /* Insert*/
            for (i in insertResult.indices) {
                if (insertResult[i] == -1L) updateList.add(objList[i])
            }

            /* Update*/
            if (updateList.isNotEmpty()) update(updateList)

            /* Purge (신규 필드는 ID가 0이기때문에 삭제안됨)*/
            purgeList?.let { _pur ->
                delete(_pur)
            }
        }
    }
}