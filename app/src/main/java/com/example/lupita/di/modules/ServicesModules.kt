package com.example.lupita.di.modules

import com.example.lupita.network.services.SitesServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModules {

    @Provides
    @Singleton
    fun catalogServices(retrofit: Retrofit): SitesServices =
        retrofit.create(SitesServices::class.java)
}