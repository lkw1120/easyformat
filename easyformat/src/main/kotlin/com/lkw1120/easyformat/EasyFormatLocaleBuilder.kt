package com.lkw1120.easyformat

import java.util.Locale

/**
 * EasyFormatLocaleBuilder
 * 
 * A builder class that allows chaining format methods with a pre-set locale.
 * Provides a fluent API for creating formatters with a specific locale.
 * 
 * Usage example:
 * ```
 * val formatter = EasyFormat.locale(Locale.US).yMMMd().Hms()
 * val result = formatter.format(now)
 * ```
 */
class EasyFormatLocaleBuilder internal constructor(
    private val locale: Locale
) {
    
    // Date format methods
    /**
     * Adds year format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun y(): EasyFormatFormatter {
        return EasyFormatFormatter("y", locale)
    }
    
    /**
     * Adds year-month format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yM(): EasyFormatFormatter {
        return EasyFormatFormatter("yM", locale)
    }
    
    /**
     * Adds year-month format with month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMM(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMM", locale)
    }
    
    /**
     * Adds year-month format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMMM(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMM", locale)
    }
    
    /**
     * Adds year-month-day format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMd(): EasyFormatFormatter {
        return EasyFormatFormatter("yMd", locale)
    }
    
    /**
     * Adds year-month-day format with month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMMd(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMd", locale)
    }
    
    /**
     * Adds year-month-day-weekday format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMMEd(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMEd", locale)
    }
    
    /**
     * Adds year-month-day format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMMMd(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMMd", locale)
    }
    
    /**
     * Adds year-month-day-weekday format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun yMMMMEd(): EasyFormatFormatter {
        return EasyFormatFormatter("yMMMMEd", locale)
    }
    
    /**
     * Adds month format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun M(): EasyFormatFormatter {
        return EasyFormatFormatter("M", locale)
    }
    
    /**
     * Adds month format with month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMM(): EasyFormatFormatter {
        return EasyFormatFormatter("MMM", locale)
    }
    
    /**
     * Adds month format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMMM(): EasyFormatFormatter {
        return EasyFormatFormatter("MMMM", locale)
    }
    
    /**
     * Adds month-day format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun Md(): EasyFormatFormatter {
        return EasyFormatFormatter("Md", locale)
    }
    
    /**
     * Adds month-day format with month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMMd(): EasyFormatFormatter {
        return EasyFormatFormatter("MMMd", locale)
    }
    
    /**
     * Adds month-day-weekday format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMMEd(): EasyFormatFormatter {
        return EasyFormatFormatter("MMMEd", locale)
    }
    
    /**
     * Adds month-day format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMMMd(): EasyFormatFormatter {
        return EasyFormatFormatter("MMMMd", locale)
    }
    
    /**
     * Adds month-day-weekday format with full month name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun MMMMEd(): EasyFormatFormatter {
        return EasyFormatFormatter("MMMMEd", locale)
    }
    
    /**
     * Adds day of month format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun d(): EasyFormatFormatter {
        return EasyFormatFormatter("d", locale)
    }

    /**
     * Adds weekday format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun E(): EasyFormatFormatter {
        return EasyFormatFormatter("E", locale)
    }
    
    /**
     * Adds weekday format with full name.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun EEEE(): EasyFormatFormatter {
        return EasyFormatFormatter("EEEE", locale)
    }
    
    // Time format methods
    /**
     * Adds 24-hour format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun H(): EasyFormatFormatter {
        return EasyFormatFormatter("H", locale)
    }
    
    /**
     * Adds 24-hour hour:minute format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun Hm(): EasyFormatFormatter {
        return EasyFormatFormatter("Hm", locale)
    }
    
    /**
     * Adds 24-hour hour:minute:second format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun Hms(): EasyFormatFormatter {
        return EasyFormatFormatter("Hms", locale)
    }
    
    /**
     * Adds 12-hour format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun h(): EasyFormatFormatter {
        return EasyFormatFormatter("h", locale)
    }
    
    /**
     * Adds 12-hour hour:minute format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun hm(): EasyFormatFormatter {
        return EasyFormatFormatter("hm", locale)
    }
    
    /**
     * Adds 12-hour hour:minute:second format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun hms(): EasyFormatFormatter {
        return EasyFormatFormatter("hms", locale)
    }
    
    /**
     * Adds minute format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun m(): EasyFormatFormatter {
        return EasyFormatFormatter("m", locale)
    }
    
    /**
     * Adds second format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun s(): EasyFormatFormatter {
        return EasyFormatFormatter("s", locale)
    }
    
    /**
     * Adds locale-specific time format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun jm(): EasyFormatFormatter {
        return EasyFormatFormatter("jm", locale)
    }
    
    /**
     * Adds locale-specific time format with seconds.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun jms(): EasyFormatFormatter {
        return EasyFormatFormatter("jms", locale)
    }

    // Timezone format methods
    /**
     * Adds timezone abbreviation format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun z(): EasyFormatFormatter {
        return EasyFormatFormatter("z", locale)
    }

    /**
     * Adds full timezone name format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun zzzz(): EasyFormatFormatter {
        return EasyFormatFormatter("zzzz", locale)
    }

    /**
     * Adds ISO 8601 timezone format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun Z(): EasyFormatFormatter {
        return EasyFormatFormatter("Z", locale)
    }

    /**
     * Adds timezone ID format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun ZZZZ(): EasyFormatFormatter {
        return EasyFormatFormatter("ZZZZ", locale)
    }

    // Week format methods
    /**
     * Adds week of year format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun w(): EasyFormatFormatter {
        return EasyFormatFormatter("w", locale)
    }

    /**
     * Adds week of month format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun W(): EasyFormatFormatter {
        return EasyFormatFormatter("W", locale)
    }

    // Quarter format methods
    /**
     * Adds quarter format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun Q(): EasyFormatFormatter {
        return EasyFormatFormatter("Q", locale)
    }

    /**
     * Adds quarter name format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun QQQ(): EasyFormatFormatter {
        return EasyFormatFormatter("QQQ", locale)
    }

    /**
     * Adds full quarter name format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun QQQQ(): EasyFormatFormatter {
        return EasyFormatFormatter("QQQQ", locale)
    }

    // Era format methods
    /**
     * Adds era format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun G(): EasyFormatFormatter {
        return EasyFormatFormatter("G", locale)
    }

    /**
     * Adds full era name format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun GGGG(): EasyFormatFormatter {
        return EasyFormatFormatter("GGGG", locale)
    }

    // ISO 8601 year format method
    /**
     * Adds ISO 8601 year format.
     * 
     * @return EasyFormatFormatter with combined format
     */
    fun u(): EasyFormatFormatter {
        return EasyFormatFormatter("u", locale)
    }
    
    /**
     * Creates a formatter with custom skeleton.
     * 
     * @param skeleton ICU skeleton string
     * @return EasyFormatFormatter instance
     * @throws IllegalArgumentException if skeleton is not supported
     */
    fun custom(skeleton: String): EasyFormatFormatter {
        return EasyFormatFormatter(skeleton, locale)
    }
} 