package com.deh.lumen.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.deh.lumen.core_data.CoreDataConstants
import com.deh.lumen.core_data.entity.CheckInEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface CheckInDao {

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} ORDER BY date DESC")
    fun observeAllCheckIns(): Flow<List<CheckInEntity>>

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} WHERE date BETWEEN :from AND :to ORDER BY date DESC")
    fun observeCheckInsForMonth(from: LocalDate, to: LocalDate): Flow<List<CheckInEntity>>

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} ORDER BY date DESC LIMIT :limit")
    fun observeRecentCheckIns(limit: Int): Flow<List<CheckInEntity>>

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} WHERE date = :date LIMIT 1")
    suspend fun getCheckInForDate(date: LocalDate): CheckInEntity?

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun getCheckInById(id: String): CheckInEntity?

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} WHERE safetyFlag IS NULL")
    suspend fun getUnclassifiedCheckIns(): List<CheckInEntity>

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} WHERE date BETWEEN :from AND :to ORDER BY date ASC")
    suspend fun getCheckInsForRange(from: LocalDate, to: LocalDate): List<CheckInEntity>

    @Query("SELECT COUNT(*) FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME}")
    fun observeTotalCount(): Flow<Int>

    @Query("SELECT date FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} ORDER BY date DESC LIMIT 1")
    suspend fun getMostRecentDate(): LocalDate?

    @Query("SELECT * FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME} ORDER BY date ASC")
    suspend fun getAllCheckInsForExport(): List<CheckInEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCheckIn(checkIn: CheckInEntity)

    @Update
    suspend fun updateCheckIn(checkIn: CheckInEntity)

    @Query("DELETE FROM ${CoreDataConstants.CHECK_IN_TABLE_NAME}")
    suspend fun deleteAllCheckIns()
}