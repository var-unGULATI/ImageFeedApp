package com.varunsoft.wariqfeed

import android.app.Application
import com.varunsoft.wariqfeed.di.appModule
import com.varunsoft.wariqfeed.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate(){
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@App)
            modules(appModule(), networkingModule())
        }
        if(BuildConfig.DEBUG) System.setProperty("kotlinx.coroutines.debug", "on")
    }

}