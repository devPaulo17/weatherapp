package com.weatherapp.data.remote.retrofit

import com.weatherapp.domain.HandleResult
import retrofit2.Response

internal inline fun <T : Any> executeRetrofitRequest(request: () -> Response<T>): HandleResult<T> {
    return try {
        val response = request.invoke()
        return if (response.isSuccessful && response.body() != null) {
            val body = response.body()
            if (body != null) {
                HandleResult.Success(body)
            } else {
                HandleResult.Error("Empty body found in this request")
            }
        } else {
            val errorBody = response.errorBody()
            val errorText = if (errorBody == null) "Error body null" else errorBody.string()
            HandleResult.Error(errorText, response.code())
        }
    }
    catch (ex: Exception) {
        HandleResult.Error(ex.message)
    }
}

fun <Api : Any, Data : Any> handleResultRetrofit(
    result: HandleResult<Api>,
    onSuccess: (Api) -> Data
): HandleResult<Data> {
    return when (result) {
        is HandleResult.Error -> {
            if(result.exception?.contains("Unable to resolve host") == true) {
                HandleResult.InternetConnectionError
            } else {
                result
            }
        }
        is HandleResult.Success -> HandleResult.Success(onSuccess.invoke(result.data))
        else -> HandleResult.Loading
    }
}

internal inline fun <Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: HandleResult<Api>,
    block: Api.() -> Domain
): HandleResult<Domain> {
    return when (apiDataState) {
        is HandleResult.Success -> {
            return HandleResult.Success(block.invoke(apiDataState.data))
        }
        is HandleResult.Error -> HandleResult.Error(apiDataState.exception)
        is HandleResult.InternetConnectionError -> HandleResult.InternetConnectionError
        else -> HandleResult.Loading
    }
}

internal inline fun <Helper : Any, Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: HandleResult<Api>,
    helper: Helper,
    block: (Helper, Api) -> Domain
): HandleResult<Domain> {
    return when (apiDataState) {
        is HandleResult.Success -> {
            return HandleResult.Success(block.invoke(helper, apiDataState.data))
        }
        is HandleResult.Error -> HandleResult.Error(apiDataState.exception)
        is HandleResult.InternetConnectionError -> HandleResult.InternetConnectionError
        else -> HandleResult.Loading
    }
}
