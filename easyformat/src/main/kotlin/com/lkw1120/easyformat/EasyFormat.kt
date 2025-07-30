/**
 * EasyFormat Library
 * 
 * A Kotlin library for ICU skeleton-based date and time formatting
 * Provides locale-aware formatting using Android's built-in ICU.
 */
package com.lkw1120.easyformat

import java.util.Locale

/**
 * EasyFormat factory object
 * 
 * Provides an API similar to Flutter's intl.DateFormat to create
 * EasyFormatFormatter instances for commonly used ICU skeletons.
 * 
 * Usage example:
 * ```
 * val dateStr = EasyFormat.yMMMEd(Locale.KOREA).format(now)
 * val timeStr = EasyFormat.Hms(Locale.US).format(Instant.now())
 * ```
 */
object EasyFormat {
    /**
     * Sets the locale for subsequent chaining operations.
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatLocaleBuilder for chaining
     */
    fun locale(locale: Locale): EasyFormatLocaleBuilder {
        return EasyFormatLocaleBuilder(locale)
    }

    /**
     * Year format (e.g., "2025")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun y(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("y", locale)
    }

    /**
     * Year-month format (e.g., "2025년 7월")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yM(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yM", locale)
    }

    /**
     * Year-month format with month name (e.g., "2025년 7월")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMM(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMM", locale)
    }

    /**
     * Year-month format with full month name (e.g., "2025년 7월")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMMM(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMM", locale)
    }

    /**
     * Year-month-day format (e.g., "2025. 7. 30.")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMd", locale)
    }

    /**
     * Year-month-day format with month name (e.g., "Jul 30, 2025")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMMd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMd", locale)
    }

    /**
     * Year-month-day-weekday format (e.g., "Wed, Jul 30, 2025")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMMEd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMEd", locale)
    }

    /**
     * Year-month-day format with full month name (e.g., "July 30, 2025")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMMMd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMMd", locale)
    }

    /**
     * Year-month-day-weekday format with full month name (e.g., "Wednesday, July 30, 2025")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun yMMMMEd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMMEd", locale)
    }

    /**
     * Month format (e.g., "7")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun M(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("M", locale)
    }

    /**
     * Month format with month name (e.g., "7월")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMM(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMM", locale)
    }

    /**
     * Month format with full month name (e.g., "7월")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMMM(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMMM", locale)
    }

    /**
     * Month-day format (e.g., "7. 30.")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun Md(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("Md", locale)
    }

    /**
     * Month-day format with month name (e.g., "Jul 30")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMMd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMMd", locale)
    }

    /**
     * Month-day-weekday format (e.g., "Wed, Jul 30")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMMEd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMMEd", locale)
    }

    /**
     * Month-day format with full month name (e.g., "July 30")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMMMd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMMMd", locale)
    }

    /**
     * Month-day-weekday format with full month name (e.g., "Wednesday, July 30")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun MMMMEd(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("MMMMEd", locale)
    }

    /**
     * Weekday format (e.g., "수")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun E(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("E", locale)
    }

    /**
     * Weekday format with full name (e.g., "수요일")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun EEEE(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("EEEE", locale)
    }

    /**
     * 24-hour format (e.g., "15")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun H(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("H", locale)
    }

    /**
     * 24-hour hour:minute format (e.g., "15:30")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun Hm(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("Hm", locale)
    }

    /**
     * 24-hour hour:minute:second format (e.g., "15:30:45")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun Hms(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("Hms", locale)
    }

    /**
     * 12-hour format (e.g., "3")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun h(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("h", locale)
    }

    /**
     * 12-hour hour:minute format (e.g., "3:30 PM")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun hm(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("hm", locale)
    }

    /**
     * 12-hour hour:minute:second format (e.g., "3:30:45 PM")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun hms(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("hms", locale)
    }

    /**
     * Minute format (e.g., "30")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun m(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("m", locale)
    }

    /**
     * Second format (e.g., "45")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun s(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("s", locale)
    }

    /**
     * Locale-specific time format (e.g., "3:30 PM")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun jm(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("jm", locale)
    }

    /**
     * Locale-specific time format with seconds (e.g., "3:30:45 PM")
     *
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun jms(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("jms", locale)
    }

    // Timezone formats
    /**
     * Timezone abbreviation format (e.g., "PST", "EST", "KST")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun z(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("z", locale)
    }

    /**
     * Full timezone name format (e.g., "Pacific Standard Time", "Eastern Standard Time")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun zzzz(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("zzzz", locale)
    }

    /**
     * ISO 8601 timezone format (e.g., "+09:00", "-08:00")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun Z(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("Z", locale)
    }

    /**
     * Timezone ID format (e.g., "Asia/Seoul", "America/Los_Angeles")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun ZZZZ(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("ZZZZ", locale)
    }

    // Week formats
    /**
     * Week of year format (e.g., "1", "52")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun w(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("w", locale)
    }

    /**
     * Week of month format (e.g., "1", "5")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun W(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("W", locale)
    }

    // Quarter formats
    /**
     * Quarter format (e.g., "1", "2", "3", "4")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun Q(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("Q", locale)
    }

    /**
     * Quarter name format (e.g., "Q1", "Q2", "Q3", "Q4")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun QQQ(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("QQQ", locale)
    }

    /**
     * Full quarter name format (e.g., "1st quarter", "2nd quarter")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun QQQQ(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("QQQQ", locale)
    }

    // Era formats
    /**
     * Era format (e.g., "AD", "BC")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun G(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("G", locale)
    }

    /**
     * Full era name format (e.g., "Anno Domini", "Before Christ")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun GGGG(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("GGGG", locale)
    }

    // ISO 8601 year format
    /**
     * ISO 8601 year format (e.g., "2025")
     * 
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     */
    fun u(locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter("u", locale)
    }

    /**
     * Creates a formatter with custom skeleton.
     * 
     * Supported skeletons:
     * - y, yM, yMMM, yMMMM, yMd, yMMMd, yMMMEd, yMMMMd, yMMMMEd
     * - M, MMM, MMMM, Md, MMMd, MMMEd, MMMMd, MMMMEd
     * - E, EEEE
     * - H, Hm, Hms, h, hm, hms, m, s, jm, jms
     * - z, zzzz, Z, ZZZZ (timezone)
     * - w, W (week)
     * - Q, QQQ, QQQQ (quarter)
     * - G, GGGG (era)
     * - u (ISO 8601 year)
     * 
     * @param skeleton ICU skeleton string
     * @param locale locale to use for formatting
     * @return EasyFormatFormatter instance
     * @throws IllegalArgumentException if skeleton is not supported
     */
    fun custom(skeleton: String, locale: Locale): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton, locale)
    }
} 