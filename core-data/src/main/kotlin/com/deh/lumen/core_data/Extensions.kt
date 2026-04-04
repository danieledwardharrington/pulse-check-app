package com.deh.lumen.core_data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format(pattern: String): String =
    this.toJavaLocalDate()
        .format(DateTimeFormatter.ofPattern(pattern))