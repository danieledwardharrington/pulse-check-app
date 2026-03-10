package com.deh.lumen.core_data.models

import com.deh.lumen.core_data.entity.UserEntity
import com.deh.lumen.core_data.entity.enum.FocusArea
import com.deh.lumen.core_data.entity.enum.UserIntention
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class User(
    val id: String,
    val displayName: String,
    val intention: UserIntention,
    val focusAreas: List<FocusArea>,
    val reminderTime: LocalTime,
    val cloudBackupEnabled: Boolean,
    val biometricsEnabled: Boolean,
    val wellbeingMonitoringEnabled: Boolean,
    val patternDetectionEnabled: Boolean,
    val weeklyInsightDay: DayOfWeek,
    val createdAt: LocalDateTime,
    val privacyAcknowledgedAt: LocalDateTime?
)

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        displayName = displayName,
        intention = intention,
        focusAreas = focusAreas,
        reminderTime = reminderTime,
        cloudBackupEnabled = cloudBackupEnabled,
        biometricsEnabled = biometricsEnabled,
        wellbeingMonitoringEnabled = wellbeingMonitoringEnabled,
        patternDetectionEnabled = patternDetectionEnabled,
        weeklyInsightDay = weeklyInsightDay,
        createdAt = createdAt,
        privacyAcknowledgedAt = privacyAcknowledgedAt
    )
}