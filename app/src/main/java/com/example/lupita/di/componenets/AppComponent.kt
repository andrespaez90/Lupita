package com.example.lupita.di.componenets

import android.content.Context
import com.example.lupita.managers.preferences.PrefsManager
import com.google.gson.Gson
import retrofit2.Retrofit

interface AppComponent {

    fun preferenceManager(): PrefsManager

    fun context(): Context

    fun gson(): Gson

    /**
     * APIs
     */

    fun retrofit(): Retrofit

}