package com.deh.lumen.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deh.lumen.core_data.entity.enum.FocusArea
import com.deh.lumen.core_data.entity.enum.UserIntention
import com.deh.lumen.core_data.format
import com.deh.lumen.core_data.models.InsightDay
import com.deh.lumen.core_data.models.UserProfile
import com.deh.lumen.core_ui.composables.LumenChevronButton
import com.deh.lumen.core_ui.composables.LumenSwitch
import com.deh.lumen.core_ui.composables.ThreeCardRow
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.profile.R
import com.deh.lumen.profile.models.ProfileItem
import com.deh.lumen.profile.models.ProfileState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import java.util.Locale
import kotlin.time.Clock

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    profileState: ProfileState.Ready,
    onCheckInItemDialogToggled: () -> Unit,
    onNotificationItemSwitchChange: (Boolean, Int) -> Unit,
    onCheckInTimeConfirmed: (LocalTime) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = LumenTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item(key = "Header") {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.profile),
                    style = LumenTheme.typography.headlineMedium,
                    color = LumenTheme.colors.onSurface
                )

                Text(
                    text = stringResource(R.string.account_and_preferences),
                    style = LumenTheme.typography.bodySmall,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }
        }

        item(key = "Profile Info") {
            ProfileInfoCard(
                startComposable = {
                    UserAvatar(
                        firstInitial = profileState.userProfile.displayName.first()
                    )
                },
                middleComposable = {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = profileState.userProfile.displayName,
                            style = LumenTheme.typography.titleLarge.copy(
                                fontStyle = FontStyle.Normal,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            ),
                            color = LumenTheme.colors.onSurface
                        )

                        Text(
                            text = stringResource(
                                R.string.member_since,
                                "${profileState.userProfile.memberSince.month.name.lowercase().capitalize(
                                    Locale.getDefault())} ${profileState.userProfile.memberSince.year}"
                            ),
                            style = LumenTheme.typography.bodySmall,
                            color = LumenTheme.colors.onSurfaceVariant
                        )
                    }
                },
                endComposable =  {
                    IconButton(
                        onClick = {
                            // TODO: Edit user name
                        },
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(LumenTheme.colors.onPrimary.copy(alpha = 0.05f))
                                .clip(CircleShape)
                                .border(width = 1.dp, color = LumenTheme.colors.onPrimary.copy(alpha = 0.07f))
                        ) {
                            Icon(
                                modifier = Modifier.align(Alignment.Center),
                                painter = painterResource(R.drawable.ic_edit),
                                contentDescription = "Edit name",
                                tint = LumenTheme.colors.onSurfaceVariant
                            )
                        }
                    }
                }
            )
        }

        item(key = "Streak") {
            StreakColumn(profileState = profileState)
        }

        item(key = "All Time") {
            AllTimeColumn(profileState = profileState)
        }

        item(key = "Check-in Section") {
            if (profileState.showDialog) {
                TimePickerDialog(
                    checkInTime = profileState.userProfile.reminderTime,
                    onDismissRequest = onCheckInItemDialogToggled,
                    onConfirmTime = onCheckInTimeConfirmed
                )
            }
            ProfileSection(
                sectionTitle = R.string.check_in,
                profileItems = checkInSectionItems(
                    onDialogToggled = onCheckInItemDialogToggled
                )
            )
        }

        item(key = "Notifications and Privacy") {
            ProfileSection(
                sectionTitle = R.string.notifications_and_privacy,
                profileItems = notificationsSectionItems(
                    profileState = profileState,
                    onCheckChange = onNotificationItemSwitchChange
                )
            )
        }

        item(key = "Support Lumen") {

        }
    }
}

@Composable
private fun StreakColumn(profileState: ProfileState.Ready) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ColumnTitle(R.string.current_streak)

        ProfileInfoCard(
            startComposable = {
                Icon(
                    painter = painterResource(R.drawable.ic_flame),
                    contentDescription = "Current streak",
                    tint = if (profileState.userProfile.currentStreak > 0) {
                        LumenTheme.colors.tertiary
                    } else {
                        LumenTheme.colors.onSurfaceVariant.copy(alpha = 0.4f)
                    }
                )
            },
            middleComposable = {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = profileState.userProfile.currentStreak.toString(),
                        style = LumenTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Normal),
                        color = if (profileState.userProfile.currentStreak > 0) {
                            LumenTheme.colors.tertiary
                        } else {
                            LumenTheme.colors.onSurfaceVariant.copy(alpha = 0.4f)
                        }
                    )

                    Text(
                        text = stringResource(R.string.days_in_a_row),
                        style = LumenTheme.typography.bodySmall,
                        color = LumenTheme.colors.onSurfaceVariant
                    )
                }
            },
            endComposable =  {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = profileState.userProfile.bestStreak.toString(),
                        style = LumenTheme.typography.titleSmall,
                        color = LumenTheme.colors.onSurface
                    )

                    Text(
                        text = stringResource(R.string.personal_best).uppercase(),
                        style = LumenTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = LumenTheme.colors.onSurfaceVariant
                    )
                }
            }
        )
    }
}

@Composable
private fun AllTimeColumn(
    profileState: ProfileState.Ready
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val bestMonthYear = profileState.userProfile.bestMonthDate.year
        val isCurrentYear = bestMonthYear == Clock.System.todayIn(TimeZone.currentSystemDefault()).year

        ColumnTitle(R.string.all_time)

        ThreeCardRow(
            horizontalPaddingValue = 16,
            firstCardTitle = profileState.userProfile.totalCheckInCount.toString(),
            secondCardTitle = profileState.userProfile.averageMoodScore.toString(),
            thirdCardTitle = profileState
                .userProfile
                .bestMonthDate
                .format("MMM"),
            firstCardDescription = stringResource(R.string.total_check_ins),
            secondCardDescription = stringResource(R.string.average_mood_score),
            thirdCardDescription = if (isCurrentYear) stringResource(R.string.best_month) else bestMonthYear.toString(),
            firstCardTitleColor = LumenTheme.colors.tertiary,
            secondCardTitleColor = getMoodTitleColor(profileState.userProfile.averageMoodScore),
            thirdCardTitleColor = LumenTheme.colors.tertiary,
            containerColor = LumenTheme.colors.surfaceVariant,
            outlineColor = LumenTheme.colors.outline
        )
    }
}

@Composable
private fun getMoodTitleColor(moodScore: Float): Color {
    return when {
        moodScore >= 4.5f -> LumenTheme.extendedColors.moodGreat
        moodScore >= 3.5f -> LumenTheme.extendedColors.moodGood
        moodScore >= 2.5f -> LumenTheme.extendedColors.moodOkay
        moodScore >= 1.5f -> LumenTheme.extendedColors.moodLow
        else -> LumenTheme.extendedColors.moodStruggling
    }
}

@Composable
private fun ColumnTitle(titleRes: Int) {
    Text(
        text = stringResource(titleRes).capitalize(Locale.getDefault()),
        style = LumenTheme.typography.titleSmall,
        color = LumenTheme.colors.onSurfaceVariant
    )
}

@Composable
private fun checkInSectionItems(onDialogToggled: () -> Unit): List<ProfileItem> {
    return listOf(
        ProfileItem(
            iconRes = R.drawable.ic_reminder,
            iconColor = LumenTheme.colors.secondary,
            iconBackgroundColor = LumenTheme.colors.secondary.copy(alpha = 0.15f),
            titleRes = R.string.daily_reminder_title,
            descriptionRes = R.string.daily_reminder_description,
            onClick = {},
            endAction = {
                LumenChevronButton(
                    onClick = onDialogToggled
                )
            }
        ),
        ProfileItem(
            iconRes = R.drawable.ic_insight_delivery,
            iconColor = LumenTheme.colors.onSurfaceVariant,
            iconBackgroundColor = LumenTheme.colors.onSurfaceVariant.copy(alpha = 0.15f),
            titleRes = R.string.insight_delivery_title,
            descriptionRes = R.string.insight_delivery_description,
            onClick = {},
            endAction = {
                LumenChevronButton(
                    onClick = onDialogToggled
                )
            }
        ),
        ProfileItem(
            iconRes = R.drawable.ic_focus_areas,
            iconColor = LumenTheme.colors.primary,
            iconBackgroundColor = LumenTheme.colors.primary.copy(alpha = 0.15f),
            titleRes = R.string.focus_areas_title,
            descriptionRes = R.string.focus_areas_description,
            onClick = {},
            endAction = {
                LumenChevronButton(
                    onClick = onDialogToggled
                )
            }
        )
    )
}

@Composable
private fun notificationsSectionItems(profileState: ProfileState.Ready, onCheckChange: (Boolean, Int) -> Unit): List<ProfileItem> {
    return listOf(
        ProfileItem(
            iconRes = R.drawable.ic_notifications,
            iconColor = LumenTheme.colors.secondary,
            iconBackgroundColor = LumenTheme.colors.secondary.copy(alpha = 0.15f),
            titleRes = R.string.push_notifications_title,
            descriptionRes = R.string.push_notifications_description,
            onClick = { },
            endAction = {
                LumenSwitch(
                    isChecked = profileState.userProfile.notificationsEnabled,
                    onCheckChange = onCheckChange,
                    itemTitleRes = R.string.push_notifications_title
                )
            }
        ),
        ProfileItem(
            iconRes = R.drawable.ic_app_lock,
            iconColor = LumenTheme.colors.primary,
            iconBackgroundColor = LumenTheme.colors.primary.copy(alpha = 0.15f),
            titleRes = R.string.app_lock_title,
            descriptionRes = R.string.app_lock_description,
            onClick = { },
            endAction = {
                LumenSwitch(
                    isChecked = profileState.userProfile.appLockEnabled,
                    onCheckChange = onCheckChange,
                    itemTitleRes = R.string.app_lock_title
                )
            }
        ),
        ProfileItem(
            iconRes = R.drawable.ic_cloud_backup,
            iconColor = LumenTheme.colors.onSurfaceVariant,
            iconBackgroundColor = LumenTheme.colors.onSurfaceVariant.copy(alpha = 0.15f),
            titleRes = R.string.cloud_backup_title,
            descriptionRes = R.string.cloud_backup_description,
            onClick = { },
            endAction = {
                LumenSwitch(
                    isChecked = profileState.userProfile.cloudEnabled,
                    onCheckChange = onCheckChange,
                    itemTitleRes = R.string.cloud_backup_title
                )
            }
        ),
        ProfileItem(
            iconRes = R.drawable.ic_wellbeing,
            iconColor = LumenTheme.extendedColors.moodGood,
            iconBackgroundColor = LumenTheme.extendedColors.moodGood.copy(alpha = 0.15f),
            titleRes = R.string.monitoring_title,
            descriptionRes = R.string.monitoring_description,
            onClick = { },
            endAction = {
                LumenSwitch(
                    isChecked = profileState.userProfile.monitoringEnabled,
                    onCheckChange = onCheckChange,
                    itemTitleRes = R.string.monitoring_title
                )
            }
        )
    )
}

@Composable
@Preview
private fun PreviewProfileContent() {
    LumenTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = LumenTheme.colors.surface)
        ) {
            ProfileContent(
                profileState = fakeProfileState(),
                onNotificationItemSwitchChange = { _, _ -> },
                onCheckInItemDialogToggled = {},
                onCheckInTimeConfirmed = {}
            )
        }
    }
}

private fun fakeProfileState(): ProfileState.Ready {
    return ProfileState.Ready(
        userProfile = UserProfile(
            id = "1",
            displayName = "Daniel",
            currentStreak = 31,
            bestStreak = 44,
            memberSince = LocalDate(2026, 1, 1),
            reminderTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time,
            focusAreas = listOf(FocusArea.CREATIVITY, FocusArea.FINANCES, FocusArea.FAMILY),
            notificationsEnabled = true,
            appLockEnabled = false,
            cloudEnabled = false,
            monitoringEnabled = true,
            insightDay = InsightDay.SUNDAY,
            totalCheckInCount = 109,
            averageMoodScore = 3.8f,
            bestMonthDate = LocalDate(2026, 1, 1),
            intention = UserIntention.SUPPORT_THERAPY
        )
    )
}