@file:Suppress("unused")

package com.submission.dicoding.gamecatalogue

import android.app.Application
import com.submission.dicoding.core.di.databaseModule
import com.submission.dicoding.core.di.networkModule
import com.submission.dicoding.core.di.repositoryModule
import com.submission.dicoding.gamecatalogue.di.useCaseModule
import com.submission.dicoding.gamecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}