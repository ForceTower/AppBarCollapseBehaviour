package dev.forcetower.picpay

import android.app.Application
import timber.log.Timber

class PicPayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}