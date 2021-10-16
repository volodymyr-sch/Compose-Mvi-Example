package com.vsch.mvi.data.di

import com.vsch.mvi.data.TodoDataSource
import com.vsch.mvi.data.TodoLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    @ViewModelScoped
    fun bindTodoDataSource(todoDataSource: TodoLocalDataSource): TodoDataSource

}