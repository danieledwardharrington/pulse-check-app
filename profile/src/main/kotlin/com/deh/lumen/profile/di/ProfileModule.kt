package com.deh.lumen.profile.di

import com.deh.lumen.core_data.database.LumenDatabase
import com.deh.lumen.core_data.repository.CheckInRepository
import com.deh.lumen.core_data.repository.UserRepository
import com.deh.lumen.profile.usecase.DeleteAllDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ProfileModule {
    @Provides
    @ViewModelScoped
    fun provideDeleteAllDataUseCase(
        userRepository: UserRepository,
        checkInRepository: CheckInRepository,
        database: LumenDatabase
    ): DeleteAllDataUseCase =
        DeleteAllDataUseCase(
            userRepository, checkInRepository, database
        )
}