package com.deh.lumen.checkin.usecase

import com.deh.lumen.core_data.repository.CheckInRepository
import javax.inject.Inject

class HasCheckedInUseCase @Inject constructor(
    private val checkInRepository: CheckInRepository
) {
    suspend operator fun invoke(): Boolean {
        return checkInRepository.hasCheckedInToday()
    }
}