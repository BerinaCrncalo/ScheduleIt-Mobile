package com.example.scheduleit.data.converter

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.Date

object DateTimeConverterUtil {

    private val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

    fun formatDate(localDate: LocalDate): String {
        return localDate.format(formatter)
    }

    /**
     * Converts a ThreeTenBP LocalDate to java.util.Date
     */
    fun localDateToDate(localDate: LocalDate): Date {
        val instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        return Date(instant.toEpochMilli()) // works on API 24+
    }

    /**
     * Converts java.util.Date to ThreeTenBP LocalDate
     */
    fun dateToLocalDate(date: Date): LocalDate {
        return org.threeten.bp.Instant.ofEpochMilli(date.time)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
}
