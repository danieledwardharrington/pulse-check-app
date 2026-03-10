package com.deh.lumen.core_data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deh.lumen.core_data.CoreDataConstants
import com.deh.lumen.core_data.converters.FocusAreaConverter
import com.deh.lumen.core_data.converters.LocalDateConverter
import com.deh.lumen.core_data.converters.LocalDateTimeConverter
import com.deh.lumen.core_data.converters.LocalTimeConverter
import com.deh.lumen.core_data.converters.UserIntentionConverter
import com.deh.lumen.core_data.dao.CheckInDao
import com.deh.lumen.core_data.dao.UserDao
import com.deh.lumen.core_data.entity.CheckInEntity
import com.deh.lumen.core_data.entity.UserEntity
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory

@Database(
    entities = [
        UserEntity::class,
        CheckInEntity::class
    ],
    version = CoreDataConstants.DATABASE_VERSION,
    exportSchema = true
)
@TypeConverters(
    FocusAreaConverter::class,
    UserIntentionConverter::class,
    LocalTimeConverter::class,
    LocalDateConverter::class,
    LocalDateTimeConverter::class
)
abstract class LumenDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checkInDao(): CheckInDao

    companion object {

        @Volatile
        private var instance: LumenDatabase? = null

        fun getInstance(
            context: Context,
            passphrase: ByteArray
        ): LumenDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, passphrase).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(
            context: Context,
            passphrase: ByteArray
        ): LumenDatabase {
            val factory = SupportOpenHelperFactory(passphrase)
            passphrase.fill(0)

            return Room.databaseBuilder(
                context.applicationContext,
                LumenDatabase::class.java,
                CoreDataConstants.DATABASE_NAME
            )
                .openHelperFactory(factory)
                .addMigrations(*Migrations.ALL)
                .fallbackToDestructiveMigrationOnDowngrade(false)
                .build()
        }
    }
}