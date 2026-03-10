package com.deh.lumen.core_data.di

import android.content.Context
import androidx.room.Room
import com.deh.lumen.core_data.CoreDataConstants.DATABASE_NAME
import com.deh.lumen.core_data.dao.CheckInDao
import com.deh.lumen.core_data.dao.UserDao
import com.deh.lumen.core_data.database.LumenDatabase
import com.deh.lumen.core_data.repository.CheckInRepository
import com.deh.lumen.core_data.repository.UserRepository
import com.deh.lumen.core_data.security.PassphraseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {
    @Provides
    @Singleton
    fun provideLumenDatabase(
        @ApplicationContext context: Context,
        passphraseManager: PassphraseManager
    ): LumenDatabase {
        val passphrase = runBlocking { passphraseManager.getOrCreatePassphrase() }
        val factory    = SupportOpenHelperFactory(passphrase)
        passphrase.fill(0)
        return Room.databaseBuilder(
            context,
            LumenDatabase::class.java,
            DATABASE_NAME
        )
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: LumenDatabase): UserDao =
        db.userDao()

    @Provides
    @Singleton
    fun provideCheckInDao(db: LumenDatabase): CheckInDao =
        db.checkInDao()

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepository(userDao)

    @Provides
    @Singleton
    fun provideCheckInRepository(
        checkInDao: CheckInDao
    ): CheckInRepository = CheckInRepository(checkInDao)

    @Provides
    @Singleton
    fun providePassphraseManager(
        @ApplicationContext context: Context
    ): PassphraseManager = PassphraseManager(context)
}