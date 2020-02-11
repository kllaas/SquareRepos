package com.example.squarerepos

import androidx.appcompat.app.AppCompatDelegate
import com.example.squarerepos.di.app.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    companion object {
        var INSTANCE: App? = null
            private set
    }
}