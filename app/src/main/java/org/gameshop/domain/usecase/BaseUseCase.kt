package org.gameshop.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gameshop.data.response.GenericResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseUseCase<I, O>() {


    abstract suspend fun invoke(param: I): Flow<Result<O>>

    fun <I, P, R, O> handleApiCall(
        param: I,
        requestMapper: (I) -> (P),
        apiCall: suspend (P) -> Response<GenericResponse<R>>,
        responseMapper: (R) -> (O)
    ): Flow<Result<O>> {
        return flow {
            try {
                val mappedParam = requestMapper(param)
                val response = apiCall(mappedParam)
                if (response.isSuccessful && response.body() != null) {
                    val mapped = responseMapper(response.body()!!.data as R)
                    emit(Result.success(mapped))
                } else
                    emit(Result.failure(Throwable("Data Null")))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> emit(Result.failure(Throwable("IO")))
                    is HttpException -> emit(Result.failure(Throwable("HTTP")))
                }
            }
        }
    }

}

