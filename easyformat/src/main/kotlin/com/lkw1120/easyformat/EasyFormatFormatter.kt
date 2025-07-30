/**
 * EasyFormat Library
 * 
 * A Kotlin library for ICU skeleton-based date and time formatting
 * Provides locale-aware formatting using Android's built-in ICU.
 */
package com.lkw1120.easyformat

import android.icu.text.DateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

/**
 * ICU skeleton-based date/time formatter
 * 
 * Creates locale-aware formatters from skeleton strings using Android's built-in ICU.
 * Fully compatible with java.time types and provides an API similar to Flutter's intl.DateFormat.
 * Supports method chaining for combining date and time formats.
 * 
 * @param skeleton ICU skeleton string (e.g., "yMd", "yMMMEd", "Hms")
 * @param locale locale to use for formatting
 */
class EasyFormatFormatter internal constructor(
    private val skeleton: String,
    private val locale: Locale
) {
    private val dateFormat: DateFormat by lazy {
        createDateFormatFromSkeleton(skeleton, locale)
    }

    /**
     * Formats an Instant.
     * 
     * @param instant the Instant to format
     * @return formatted string
     */
    fun format(instant: Instant): String {
        val date = Date.from(instant)
        return dateFormat.format(date)
    }

    /**
     * Formats a LocalDateTime.
     * 
     * @param dateTime the LocalDateTime to format
     * @return formatted string
     */
    fun format(dateTime: LocalDateTime): String {
        val instant = dateTime.atZone(ZoneId.systemDefault()).toInstant()
        return format(instant)
    }

    /**
     * Formats a java.util.Date.
     * 
     * @param date the Date to format
     * @return formatted string
     */
    fun format(date: Date): String {
        return dateFormat.format(date)
    }

    // Date format chaining methods
    /**
     * Adds year format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun y(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " y", locale ?: this.locale)
    }

    /**
     * Adds year-month format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yM(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yM", locale ?: this.locale)
    }

    /**
     * Adds year-month format with month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMM(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMM", locale ?: this.locale)
    }

    /**
     * Adds year-month format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMMM(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMMM", locale ?: this.locale)
    }

    /**
     * Adds year-month-day format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMd", locale ?: this.locale)
    }

    /**
     * Adds year-month-day format with month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMMd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMMd", locale ?: this.locale)
    }

    /**
     * Adds year-month-day-weekday format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMMEd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMMEd", locale ?: this.locale)
    }

    /**
     * Adds year-month-day format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMMMd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMMMd", locale ?: this.locale)
    }

    /**
     * Adds year-month-day-weekday format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun yMMMMEd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " yMMMMEd", locale ?: this.locale)
    }

    /**
     * Adds month format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun M(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " M", locale ?: this.locale)
    }

    /**
     * Adds month format with month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMM(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMM", locale ?: this.locale)
    }

    /**
     * Adds month format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMMM(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMMM", locale ?: this.locale)
    }

    /**
     * Adds month-day format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun Md(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " Md", locale ?: this.locale)
    }

    /**
     * Adds month-day with month name format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMMd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMMd", locale ?: this.locale)
    }

    /**
     * Adds month-day-weekday format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMMEd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMMEd", locale ?: this.locale)
    }

    /**
     * Adds month-day format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMMMd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMMMd", locale ?: this.locale)
    }

    /**
     * Adds month-day-weekday format with full month name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun MMMMEd(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " MMMMEd", locale ?: this.locale)
    }

    /**
     * Adds weekday format to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun E(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " E", locale ?: this.locale)
    }

    /**
     * Adds weekday format with full name to the current formatter.
     * 
     * @param locale locale to use for the date part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun EEEE(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " EEEE", locale ?: this.locale)
    }

    // Time format chaining methods
    /**
     * Adds 24-hour format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun H(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " H", locale ?: this.locale)
    }

    /**
     * Adds 24-hour hour:minute format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun Hm(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " Hm", locale ?: this.locale)
    }

    /**
     * Adds 24-hour hour:minute:second format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun Hms(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " Hms", locale ?: this.locale)
    }

    /**
     * Adds 12-hour format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun h(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " h", locale ?: this.locale)
    }

    /**
     * Adds 12-hour hour:minute format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun hm(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " hm", locale ?: this.locale)
    }

    /**
     * Adds 12-hour hour:minute:second format to the current format
     */
    fun hms(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " hms", locale ?: this.locale)
    }

    /**
     * Adds minute format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun m(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " m", locale ?: this.locale)
    }

    /**
     * Adds second format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun s(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " s", locale ?: this.locale)
    }

    /**
     * Adds locale-specific time format to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun jm(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " jm", locale ?: this.locale)
    }

    /**
     * Adds locale-specific time format with seconds to the current formatter.
     * 
     * @param locale locale to use for the time part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun jms(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " jms", locale ?: this.locale)
    }

    // Timezone chaining methods
    /**
     * Adds timezone abbreviation to the current formatter.
     * 
     * @param locale locale to use for the timezone part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun z(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " z", locale ?: this.locale)
    }

    /**
     * Adds full timezone name to the current formatter.
     * 
     * @param locale locale to use for the timezone part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun zzzz(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " zzzz", locale ?: this.locale)
    }

    /**
     * Adds ISO 8601 timezone format to the current formatter.
     * 
     * @param locale locale to use for the timezone part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun Z(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " Z", locale ?: this.locale)
    }

    /**
     * Adds timezone ID to the current formatter.
     * 
     * @param locale locale to use for the timezone part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun ZZZZ(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " ZZZZ", locale ?: this.locale)
    }

    // Week chaining methods
    /**
     * Adds week of year to the current formatter.
     * 
     * @param locale locale to use for the week part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun w(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " w", locale ?: this.locale)
    }

    /**
     * Adds week of month to the current formatter.
     * 
     * @param locale locale to use for the week part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun W(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " W", locale ?: this.locale)
    }

    // Quarter chaining methods
    /**
     * Adds quarter to the current formatter.
     * 
     * @param locale locale to use for the quarter part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun Q(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " Q", locale ?: this.locale)
    }

    /**
     * Adds quarter name to the current formatter.
     * 
     * @param locale locale to use for the quarter part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun QQQ(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " QQQ", locale ?: this.locale)
    }

    /**
     * Adds full quarter name to the current formatter.
     * 
     * @param locale locale to use for the quarter part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun QQQQ(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " QQQQ", locale ?: this.locale)
    }

    // Era chaining methods
    /**
     * Adds era to the current formatter.
     * 
     * @param locale locale to use for the era part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun G(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " G", locale ?: this.locale)
    }

    /**
     * Adds full era name to the current formatter.
     * 
     * @param locale locale to use for the era part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun GGGG(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " GGGG", locale ?: this.locale)
    }

    // ISO 8601 year chaining method
    /**
     * Adds ISO 8601 year to the current formatter.
     * 
     * @param locale locale to use for the year part (optional, uses current locale if not specified)
     * @return new EasyFormatFormatter with combined format
     */
    fun u(locale: Locale? = null): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton + " u", locale ?: this.locale)
    }

    /**
     * Creates ICU DateFormat from skeleton string.
     * 
     * Uses Android's built-in ICU to generate locale-aware patterns.
     * 
     * @param skeleton ICU skeleton string
     * @param locale locale
     * @return DateFormat instance
     */
    private fun createDateFormatFromSkeleton(skeleton: String, locale: Locale): DateFormat {
        return DateFormat.getPatternInstance(skeleton, locale)
    }
} 