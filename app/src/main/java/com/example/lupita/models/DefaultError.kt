package com.example.lupita.models

import com.google.gson.annotations.SerializedName

class DefaultError {

    @SerializedName("error")
    val error: Error? = null

    inner class Error(@SerializedName("message") val message: String?,
                      @SerializedName("code") val errorCode: Any?) {

        @Transient
        var serviceUrl: String = ""
    }
}