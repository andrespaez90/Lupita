package com.example.lupita

import com.example.lupita.di.InjectableApplication
import com.example.lupita.di.componenets.DaggerMainComponent
import com.example.lupita.di.componenets.MainComponent

class SelfApplication : InjectableApplication() {

    lateinit var appComponent: MainComponent

    companion object {

        protected lateinit var sInstance: SelfApplication

        fun getAppInstance(): SelfApplication =
            sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        initializeInjector()
    }

    override fun initializeInjector() {
       appComponent = DaggerMainComponent
            .builder()
            .application(this).build()
        appComponent.inject(this)
    }
}