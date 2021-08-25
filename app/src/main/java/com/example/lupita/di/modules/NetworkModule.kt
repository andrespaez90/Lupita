package com.example.lupita.di.modules

import com.example.lupita.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val TIME_OUT = 6

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun retrofitServices(gson: Gson): Retrofit {
        val httpClient = getHttpClientBuilder().apply {
            addInterceptor(addAuthentication())
        }
        return getRetrofitBuilder(httpClient.build(), "https://api.mercadolibre.com/", gson)
            .build()
    }

    private fun getRetrofitBuilder(httpClient: OkHttpClient, url: String, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


    private fun addAuthentication(): Interceptor {

        return Interceptor { chain ->

            val newBuilder = chain.request().newBuilder()

            newBuilder.method(chain.request().method(), chain.request().body())

            newBuilder.header("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")

            newBuilder.header("x-rapidapi-key", "6870677d8bmshfce16b595456a14p17e57bjsne3ad8d11ad74")

            chain.proceed(newBuilder.build())
        }
    }

    private fun getHttpClientBuilder(): OkHttpClient.Builder {

        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {

            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
            .create()
    }

}