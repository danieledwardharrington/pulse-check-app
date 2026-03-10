package com.deh.lumen.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.deh.lumen.core_data.CoreDataConstants
import com.deh.lumen.core_data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM ${CoreDataConstants.USER_TABLE_NAME} LIMIT 1")
    fun observeUser(): Flow<UserEntity?>

    @Query("SELECT * FROM ${CoreDataConstants.USER_TABLE_NAME} LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM ${CoreDataConstants.USER_TABLE_NAME}")
    suspend fun deleteUser()
}