package com.example.lupita.di.modules

import com.example.lupita.network.services.SitesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ServicesModules {

    @Provides
    @Singleton
    fun catalogServices(retrofit: Retrofit): SitesServices =
        retrofit.create(SitesServices::class.java)
}