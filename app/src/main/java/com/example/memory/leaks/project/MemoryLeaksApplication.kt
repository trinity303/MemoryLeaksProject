package com.example.memory.leaks.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author kebirova@kolesa.kz
 */
class MemoryLeaksApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MemoryLeaksApplication)
            modules(diModule)
        }
    }
}