package com.deh.lumen.core_data

import com.deh.lumen.core_data.entity.UserEntity
import com.deh.lumen.core_data.models.InsightDay
import com.deh.lumen.core_data.models.UserProfile
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.todayIn
import java.time.format.DateTimeFormatter
import kotlin.time.Clock

fun LocalDate.format(pattern: String): String =
    this.toJavaLocalDate()
        .format(DateTimeFormatter.ofPattern(pattern))

fun UserEntity.toUserProfile(): UserProfile {
    return UserProfile(
        id = this.id,
        displayName = this.displayName,
        intention = this.intention,
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

fun UserProfile.toUserEntity(): UserEntity =
    UserEntity(
        id = this.id,
        displayName = this.displayName,
        intention = this.intention,
        focusAreas = this.focusAreas,
        reminderTime = this.reminderTime,
        cloudBackupEnabled = this.cloudEnabled,
        biometricsEnabled = this.appLockEnabled,
        wellbeingMonitoringEnabled = this.monitoringEnabled,
        weeklyInsightDay = this.insightDay.toDayOfWeek()
    )

fun DayOfWeek.toInsightDay(): InsightDay {
    return when (this) {
        DayOfWeek.MONDAY -> InsightDay.MONDAY
        DayOfWeek.TUESDAY -> InsightDay.TUESDAY
        DayOfWeek.WEDNESDAY -> InsightDay.WEDNESDAY
        DayOfWeek.THURSDAY -> InsightDay.THURSDAY
        DayOfWeek.FRIDAY -> InsightDay.FRIDAY
        DayOfWeek.SATURDAY -> InsightDay.SATURDAY
        DayOfWeek.SUNDAY -> InsightDay.SUNDAY
    }
}

fun InsightDay.toDayOfWeek(): DayOfWeek {
    return when (this) {
        InsightDay.MONDAY -> DayOfWeek.MONDAY
        InsightDay.TUESDAY -> DayOfWeek.TUESDAY
        InsightDay.WEDNESDAY -> DayOfWeek.WEDNESDAY
        InsightDay.THURSDAY -> DayOfWeek.THURSDAY
        InsightDay.FRIDAY -> DayOfWeek.FRIDAY
        InsightDay.SATURDAY -> DayOfWeek.SATURDAY
        InsightDay.SUNDAY -> DayOfWeek.SUNDAY
    }
}