package com.deh.lumen.core_data.repository

import android.database.sqlite.SQLiteConstraintException
import com.deh.lumen.core_data.QuestionAnswerPair
import com.deh.lumen.core_data.dao.CheckInDao
import com.deh.lumen.core_data.entity.CheckInEntity
import com.deh.lumen.core_data.models.SafetyStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.Clock

class CheckInRepository @Inject constructor(
    private val checkInDao: CheckInDao
) {
    fun observeAllCheckIns(): Flow<List<CheckInEntity>> =
        checkInDao.observeAllCheckIns()

    fun observeCheckInsForMonth(
        from: LocalDate,
        to: LocalDate
    ): Flow<List<CheckInEntity>> =
        checkInDao.observeCheckInsForMonth(from, to)

    fun observeRecentCheckIns(limit: Int = 3): Flow<List<CheckInEntity>> =
        checkInDao.observeRecentCheckIns(limit)

    suspend fun getCheckInForDate(date: LocalDate): CheckInEntity? =
        checkInDao.getCheckInForDate(date)

    suspend fun getCheckInsForRange(
        from: LocalDate,
        to: LocalDate
    ): List<CheckInEntity> =
        checkInDao.getCheckInsForRange(from, to)

    suspend fun hasCheckedInToday(): Boolean {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return checkInDao.getMostRecentDate() == today
    }

    suspend fun getUnclassifiedCheckIns(): List<CheckInEntity> =
        checkInDao.getUnclassifiedCheckIns()

    suspend fun getAllCheckInsForExport(): List<CheckInEntity> =
        checkInDao.getAllCheckInsForExport()

    fun observeTotalCount(): Flow<Int> =
        checkInDao.observeTotalCount()

    suspend fun saveCheckIn(checkIn: CheckInEntity): Result<CheckInEntity> {
        return try {
            checkInDao.insertCheckIn(checkIn)
            val saved = checkInDao.getCheckInById(checkIn.id)
                ?: return Result.failure(
                    IllegalStateException("Check-in was inserted but could not be retrieved")
                )
            Result.success(saved)
        } catch (e: SQLiteConstraintException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateSafetyFlag(
        id: String,
        status: SafetyStatus
    ) {
        val existing = checkInDao.getCheckInById(id)
            ?: error("Cannot update safety flag — check-in $id not found")
        checkInDao.updateCheckIn(existing.copy(safetyConfidence = status.confidence))
    }

    suspend fun saveEditedAnswers(
        id: String,
        editedAnswers: List<QuestionAnswerPair>
    ) {
        val existing = checkInDao.getCheckInById(id)
            ?: error("Cannot edit — check-in $id not found")
        require(existing.date == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())) {
            "Answers can only be edited on the same day as the original check-in"
        }
        checkInDao.updateCheckIn(
            existing.copy(
                editedAnswerPairs = editedAnswers,
                editedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            )
        )
    }

    suspend fun deleteAllCheckIns() =
        checkInDao.deleteAllCheckIns()
}