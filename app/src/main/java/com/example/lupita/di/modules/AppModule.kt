package com.exammple.lupita.di.modules

import android.content.Context
import com.example.lupita.di.InjectableApplication
import com.example.lupita.managers.preferences.PrefsManager
import com.health.monitor.provider.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun appContext(application: InjectableApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun preferenceManager(context: Context): PrefsManager {
        PrefsManager.init(context)
        return PrefsManager.getInstance()
    }

    @Provides
    @Singleton
    fun resourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }
}