package com.apx6.domain.repository.boundary

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.apx6.domain.repository.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class LocalBoundaryRepository<RESULT, REQUEST> {

    fun asFlow(T: REQUEST) = flow<Resource<RESULT>> {

        /* Emit Database content first*/
        emit(Resource.Success(fetchFromLocal().first()))

        /* post to Local DB*/
        CoroutineScope(Dispatchers.IO).launch {
            postToLocal(T)
        }

        /* Retrieve posts from persistence storage and emit*/
        emitAll(
            fetchFromLocal().map {
                Resource.Success<RESULT>(it)
            }
        )
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Local Boundary Error"))
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun postToLocal(obj: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

}