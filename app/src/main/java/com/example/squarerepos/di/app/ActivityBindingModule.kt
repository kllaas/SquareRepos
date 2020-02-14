package com.example.squarerepos.di.app

import com.example.squarerepos.di.PerActivity
import com.example.squarerepos.di.PerFragment
import com.example.squarerepos.di.repos.ReposModule
import com.example.squarerepos.ui.MainActivity
import com.example.squarerepos.ui.repos.ReposFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerFragment
    @ContributesAndroidInjector(
        modules = [
            ReposModule::class
        ]
    )
    internal abstract fun reposFragment(): ReposFragment

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun reposActivity(): MainActivity

}