package com.example.lupita.utils

import com.example.lupita.models.DefaultError
import com.google.gson.Gson
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import retrofit2.HttpException
import java.io.IOException
import java.nio.charset.Charset

object ErrorUtil {

    private val CONNECTION_ERROR = "Revisa tu conexión a internet."

    @JvmStatic
    fun getMessageError(throwable: Throwable?): String {
        var errorMessage = "Server Error"
        try {
            throwable?.let { errorMessage = getMessageByErrorType(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return errorMessage
    }

    private fun getMessageByErrorType(throwable: Throwable): String {
        var errorMessage: String = throwable.cause?.message ?: throwable.message ?: CONNECTION_ERROR
        if (throwable is HttpException) {
            val copyException = HttpException(throwable.response())
            val responseBody = copyException.response()?.errorBody()
            val defaultError: DefaultError = Gson().fromJson(getBody(responseBody), DefaultError::class.java)
            defaultError.error?.message?.let { errorMessage = it }
        } else if (throwable is IOException) {
            errorMessage = CONNECTION_ERROR
        }
        return errorMessage
    }

    @JvmStatic
    fun getError(throwable: Throwable): DefaultError.Error? {
        var error: DefaultError.Error? = null
        try {
            if (throwable is HttpException) {
                val responseBody = throwable.response()?.errorBody()
                error = Gson().fromJson(getBody(responseBody), DefaultError::class.java).error
                error?.serviceUrl = throwable.response()?.raw()?.request()?.url().toString()
            } else if (throwable is IOException) {
                error = DefaultError().Error(CONNECTION_ERROR, null)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
        return error
    }

    private fun getBody(response: ResponseBody?): String? {
        val source: BufferedSource? = response?.source()
        source?.request(Long.MAX_VALUE)
        val buffer: Buffer? = source?.buffer()
        return buffer?.clone()?.readString(Charset.forName("UTF-8"))
    }
}