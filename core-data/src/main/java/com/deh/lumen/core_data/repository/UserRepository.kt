package com.deh.lumen.core_data.repository

import com.deh.lumen.core_data.dao.UserDao
import com.deh.lumen.core_data.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    // Used for the profile screen
    fun observeUser(): Flow<UserEntity?> =
        userDao.observeUser()

    suspend fun getUser(): UserEntity? =
        userDao.getUser()

    suspend fun saveUser(user: UserEntity) =
        userDao.insertUser(user)

    suspend fun updateUser(update: UserEntity.() -> UserEntity) {
        val current = userDao.getUser()
            ?: error("Cannot update user — no user exists")
        userDao.updateUser(current.update())
    }

    suspend fun deleteUser() =
        userDao.deleteUser()
}