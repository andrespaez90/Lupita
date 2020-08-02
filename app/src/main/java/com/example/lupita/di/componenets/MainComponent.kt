package com.example.lupita.di.componenets

import com.exammple.lupita.di.modules.AppModule
import com.example.lupita.di.InjectableApplication
import com.example.lupita.di.modules.NetworkModule
import com.example.lupita.di.modules.ServicesModules
import com.example.lupita.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        ServicesModules::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface MainComponent : AppComponent, AndroidInjector<InjectableApplication> {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun application(application: InjectableApplication): Builder
    }
}
