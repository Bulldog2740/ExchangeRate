package com.web.exchangerate.di

import androidx.annotation.Keep
import com.web.exchangerate.AppDelegate
import com.web.exchangerate.di.network.networkModule
import com.web.exchangerate.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Keep
object Koin {

    fun start(appDelegate: AppDelegate) {
        val koinApplication = GlobalContext.getOrNull()

        if (koinApplication != null) {
            // We already started KoinApplication
            return
        }

       val modules = listOf<Module>(
           networkModule(),
           repositoryModule(),
           bankViewModule()
       )

        startKoin {
            androidLogger(Level.NONE)
            androidContext(appDelegate)
           modules(modules)
        }
    }

    fun stop() {
        stopKoin()
    }
}