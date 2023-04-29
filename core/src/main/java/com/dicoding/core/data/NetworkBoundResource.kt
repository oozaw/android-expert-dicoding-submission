package com.dicoding.core.data

import com.dicoding.core.data.remote.response.ApiStatus
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

   private var result: Flow<Status<ResultType>> = flow {
      emit(Status.Loading())
      val dbSource = loadFromDB().first()
      if (shouldFetch(dbSource)) {
         emit(Status.Loading())
         when (val apiResponse = createCall().first()) {
            is ApiStatus.Success -> {
               saveCallResult(apiResponse.data)
               emitAll(loadFromDB().map { Status.Success(it) })
            }
            is ApiStatus.Empty -> {
               emitAll(loadFromDB().map { Status.Success(it) })
            }
            is ApiStatus.Error -> {
               onFetchFailed()
               emit(Status.Error(apiResponse.error))
            }
         }
      } else {
         emitAll(loadFromDB().map { Status.Success(it) })
      }
   }

   protected open fun onFetchFailed() {}

   protected abstract fun loadFromDB(): Flow<ResultType>

   protected abstract fun shouldFetch(data: ResultType?): Boolean

   protected abstract suspend fun createCall(): Flow<ApiStatus<RequestType>>

   protected abstract suspend fun saveCallResult(data: RequestType)

   fun asFlow(): Flow<Status<ResultType>> = result
}