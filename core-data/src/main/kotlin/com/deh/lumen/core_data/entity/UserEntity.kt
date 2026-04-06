package com.deh.lumen.core_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deh.lumen.core_data.CoreDataConstants
import com.deh.lumen.core_data.entity.enum.FocusArea
import com.deh.lumen.core_data.entity.enum.UserIntention
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = CoreDataConstants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    val id: String = Uuid.random().toString(),
    val displayName: String,
    val intention: UserIntention,
    val focusAreas: List<FocusArea>,
    val reminderTime: LocalTime,
    val cloudBackupEnabled: Boolean = false,
    val biometricsEnabled: Boolean = false,
    val wellbeingMonitoringEnabled: Boolean = true,
    val patternDetectionEnabled: Boolean = true,
    val weeklyInsightDay: DayOfWeek = DayOfWeek.SUNDAY,
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val privacyAcknowledgedAt: LocalDateTime? = null,
    val currentStreak: Int = 0,
    val bestStreak: Int = 0
)