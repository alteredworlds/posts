package com.example.twcgilbert.postsapp.ui.di

import android.arch.lifecycle.ViewModelProvider
import com.example.twcgilbert.postsapp.common.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModuleModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}