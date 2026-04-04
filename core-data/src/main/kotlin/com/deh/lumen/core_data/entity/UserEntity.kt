package com.deh.lumen.core_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deh.lumen.core_data.CoreDataConstants
import com.deh.lumen.core_data.entity.enum.FocusArea
import com.deh.lumen.core_data.entity.enum.UserIntention
import com.deh.lumen.core_data.models.UserProfile
import com.deh.lumen.core_data.models.toInsightDay
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
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

fun UserEntity.toUserProfile(): UserProfile {
    return UserProfile(
        displayName = this.displayName,
        focusAreas = this.focusAreas,
        reminderTime = this.reminderTime,
        cloudEnabled = this.cloudBackupEnabled,
        appLockEnabled = this.biometricsEnabled,
        monitoringEnabled = this.wellbeingMonitoringEnabled,
        insightDay = this.weeklyInsightDay.toInsightDay(),
        memberSince = this.createdAt.date,
        totalCheckInCount = 0,
        currentStreak = this.currentStreak,
        bestStreak = this.bestStreak,
        averageMoodScore = 0.0f,
        notificationsEnabled = false,
        bestMonthDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    )
}