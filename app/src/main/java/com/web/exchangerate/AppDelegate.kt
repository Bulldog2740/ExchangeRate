package com.web.exchangerate

import android.app.Application
import com.web.exchangerate.di.Koin
import timber.log.Timber


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // Init DI
        Koin.start(this)
    }
}
