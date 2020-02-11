package com.example.squarerepos.di.repos

import androidx.lifecycle.ViewModel
import com.example.squarerepos.di.app.ViewModelKey
import com.example.squarerepos.ui.repos.ReposFragment
import com.example.squarerepos.ui.repos.ReposViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal abstract class ReposModule {

    internal abstract fun contributeReposFragment(): ReposFragment

    @Binds
    @IntoMap
    @ViewModelKey(ReposViewModel::class)
    abstract fun bindReposViewModel(viewModel: ReposViewModel): ViewModel

}