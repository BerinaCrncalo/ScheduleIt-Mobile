package com.example.scheduleit.data.converter

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.util.Date
import org.threeten.bp.format.DateTimeFormatter

object DateTimeConverterUtil {

    fun formatDate(localDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        return localDate.format(formatter)
    }

    /**
     * Converts a ThreeTenBP LocalDate to java.util.Date
     */
    fun localDateToDate(localDate: LocalDate): Date {
        val instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        return Date(instant.toEpochMilli()) // 👈 works on API 24+
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