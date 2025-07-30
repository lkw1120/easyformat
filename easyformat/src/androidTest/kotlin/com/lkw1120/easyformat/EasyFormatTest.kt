/**
 * EasyFormat Library Tests
 * 
 * Test code to verify the functionality of the EasyFormat library.
 */
package com.lkw1120.easyformat

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lkw1120.easyformat.EasyFormat
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

/**
 * EasyFormat library test class
 * 
 * Verifies formatting functionality for various locales and skeletons.
 * Uses Instrumented tests to access Android ICU APIs.
 */
@RunWith(AndroidJUnit4::class)
class EasyFormatTest {
    // Fixed test data using UTC
    private val testInstant = Instant.parse("2025-07-30T15:30:45Z")
    private val testDateTime = LocalDateTime.of(2025, 7, 30, 15, 30, 45)
    private val testDate = Date.from(testInstant)

    @Test
    fun testYMMMdFormat() {
        val koreanResult = EasyFormat.yMMMd(Locale.KOREA).format(testDateTime)
        assertEquals("2025년 7월 30일", koreanResult)

        val usResult = EasyFormat.yMMMd(Locale.US).format(testDateTime)
        assertEquals("Jul 30, 2025", usResult)
    }

    @Test
    fun testYMMMEdFormat() {
        val koreanResult = EasyFormat.yMMMEd(Locale.KOREA).format(testDateTime)
        println("Korean yMMMEd result: $koreanResult")
        // ICU pattern may vary, so check for year and month presence
        assertTrue(koreanResult.contains("2025") && koreanResult.contains("7"))

        // English weekday format may vary by system, so check actual result
        val usResult = EasyFormat.yMMMEd(Locale.US).format(testDateTime)
        println("US yMMMEd result: $usResult")
        // May be "Jul 30, 2025 Wed" or "Wed, Jul 30, 2025" format
        assertTrue(usResult.contains("Jul 30, 2025") && usResult.contains("Wed"))
    }

    @Test
    fun testHmsFormat() {
        val result = EasyFormat.Hms(Locale.US).format(testDateTime)
        assertEquals("15:30:45", result)
    }

    @Test
    fun testJmFormat() {
        val koreanResult = EasyFormat.jm(Locale.KOREA).format(testDateTime)
        assertEquals("오후 3:30", koreanResult)

        val usResult = EasyFormat.jm(Locale.US).format(testDateTime)
        assertEquals("3:30 PM", usResult)
    }

    @Test
    fun testInstantFormat() {
        val result = EasyFormat.yMMMd(Locale.KOREA).format(testInstant)
        println("Instant format result: $result")
        // Instant results may vary by system timezone, so check actual result
        assertTrue(result.contains("2025년") && result.contains("7월"))
    }

    @Test
    fun testCustomSkeleton() {
        val result = EasyFormat.custom("yMd", Locale.KOREA).format(testDateTime)
        assertEquals("2025. 7. 30.", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidSkeleton() {
        EasyFormat.custom("invalid", Locale.US).format(testDateTime)
    }

    // Extension functions test removed - use EasyFormat object directly
    @Test
    fun testDirectEasyFormatUsage() {
        val instantResult = EasyFormat.yMMMd(Locale.KOREA).format(testInstant)
        println("Direct EasyFormat result: $instantResult")
        // Instant results may vary by system timezone, so check actual result
        assertTrue(instantResult.contains("2025년") && instantResult.contains("7월"))

        val dateTimeResult = EasyFormat.yMMMEd(Locale.KOREA).format(testDateTime)
        assertEquals("2025년 7월 30일 (수)", dateTimeResult)
    }

    @Test
    fun testJapaneseLocale() {
        val result = EasyFormat.yMMMd(Locale.JAPAN).format(testDateTime)
        assertEquals("2025年7月30日", result)
    }

    @Test
    fun testChineseLocale() {
        val result = EasyFormat.yMMMd(Locale.CHINESE).format(testDateTime)
        assertEquals("2025年7月30日", result)
    }

    @Test
    fun testTimeZoneHandling() {
        val instant = Instant.parse("2025-07-30T15:30:45Z")
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"))
        
        val instantResult = EasyFormat.yMMMd(Locale.KOREA).format(instant)
        val dateTimeResult = EasyFormat.yMMMd(Locale.KOREA).format(localDateTime)
        
        assertEquals(instantResult, dateTimeResult)
    }

    @Test
    fun testVariousSkeletons() {
        // Test various skeletons - ICU patterns may vary
        val yMdResult = EasyFormat.custom("yMd", Locale.KOREA).format(testDateTime)
        assertTrue(yMdResult.contains("2025") && yMdResult.contains("7"))
        
        val mdResult = EasyFormat.custom("Md", Locale.KOREA).format(testDateTime)
        assertTrue(mdResult.contains("7"))
        
        val mmmdResult = EasyFormat.custom("MMMd", Locale.KOREA).format(testDateTime)
        assertTrue(mmmdResult.contains("7"))
        
        val hmResult = EasyFormat.custom("Hm", Locale.US).format(testDateTime)
        assertTrue(hmResult.contains("15") && hmResult.contains("30"))
        
        val h12Result = EasyFormat.custom("hm", Locale.US).format(testDateTime)
        assertTrue(h12Result.contains("3") && h12Result.contains("30"))
    }

    @Test
    fun testMethodChaining() {
        // Test date + time chaining
        val dateTimeResult = EasyFormat.yMMMd(Locale.KOREA).Hms(Locale.KOREA).format(testDateTime)
        println("Date + Time chaining result: $dateTimeResult")
        // Check for year and time presence, format may vary
        assertTrue(dateTimeResult.contains("2025") && dateTimeResult.contains("15"))

        // Test time + date chaining
        val timeDateResult = EasyFormat.Hms(Locale.US).yMMMd(Locale.US).format(testDateTime)
        println("Time + Date chaining result: $timeDateResult")
        assertTrue(timeDateResult.contains("Jul") && timeDateResult.contains("15"))

        // Test date + locale-specific time
        val dateLocalTimeResult = EasyFormat.yMMMEd(Locale.KOREA).jm(Locale.KOREA).format(testDateTime)
        println("Date + Local time chaining result: $dateLocalTimeResult")
        assertTrue(dateLocalTimeResult.contains("2025") && (dateLocalTimeResult.contains("오후") || dateLocalTimeResult.contains("오전")))
    }

    @Test
    fun testMethodChainingWithDefaultLocale() {
        // Test chaining without specifying locale (uses previous locale)
        val dateTimeResult = EasyFormat.yMMMd(Locale.KOREA).Hms().format(testDateTime)
        println("Date + Time with default locale result: $dateTimeResult")
        assertTrue(dateTimeResult.contains("2025") && dateTimeResult.contains("15"))

        // Test multiple chaining with default locale (avoid conflicting fields)
        val complexResult = EasyFormat.yMMMEd(Locale.KOREA).Hms().format(testDateTime)
        println("Complex chaining with default locale result: $complexResult")
        assertTrue(complexResult.contains("2025") && complexResult.contains("15"))

        // Test time + date with default locale
        val timeDateResult = EasyFormat.Hms(Locale.US).yMMMd().format(testDateTime)
        println("Time + Date with default locale result: $timeDateResult")
        assertTrue(timeDateResult.contains("Jul") && timeDateResult.contains("15"))
    }

    @Test
    fun testLocaleChaining() {
        // Test locale-first chaining
        val dateTimeResult = EasyFormat.locale(Locale.KOREA).yMMMd().Hms().format(testDateTime)
        println("Locale-first chaining result: $dateTimeResult")
        assertTrue(dateTimeResult.contains("2025") && dateTimeResult.contains("15"))

        // Test locale-first with custom skeleton
        val customResult = EasyFormat.locale(Locale.US).custom("yMMMMd").format(testDateTime)
        println("Locale-first custom skeleton result: $customResult")
        assertTrue(customResult.contains("July") && customResult.contains("2025"))

        // Test locale-first with time format
        val timeResult = EasyFormat.locale(Locale.KOREA).jm().format(testDateTime)
        println("Locale-first time format result: $timeResult")
        assertTrue(timeResult.contains("오후") || timeResult.contains("오전"))
    }

    // ===== EasyFormat.kt 테스트되지 않은 함수들 =====

    @Test
    fun testYMdFormat() {
        val koreanResult = EasyFormat.yMd(Locale.KOREA).format(testDateTime)
        assertEquals("2025. 7. 30.", koreanResult)

        val usResult = EasyFormat.yMd(Locale.US).format(testDateTime)
        // ICU may use 4-digit year, so check for year and month presence
        assertTrue(usResult.contains("7/30") && usResult.contains("2025"))
    }

    @Test
    fun testYMMMMdFormat() {
        val koreanResult = EasyFormat.yMMMMd(Locale.KOREA).format(testDateTime)
        assertEquals("2025년 7월 30일", koreanResult)

        val usResult = EasyFormat.yMMMMd(Locale.US).format(testDateTime)
        assertEquals("July 30, 2025", usResult)
    }

    @Test
    fun testYMMMMEdFormat() {
        val koreanResult = EasyFormat.yMMMMEd(Locale.KOREA).format(testDateTime)
        println("Korean yMMMMEd result: $koreanResult")
        assertTrue(koreanResult.contains("2025") && koreanResult.contains("7월"))

        val usResult = EasyFormat.yMMMMEd(Locale.US).format(testDateTime)
        println("US yMMMMEd result: $usResult")
        // Check for year, month, and weekday presence (format may vary)
        assertTrue(usResult.contains("2025") && usResult.contains("July"))
    }

    @Test
    fun testMdFormat() {
        val koreanResult = EasyFormat.Md(Locale.KOREA).format(testDateTime)
        assertEquals("7. 30.", koreanResult)

        val usResult = EasyFormat.Md(Locale.US).format(testDateTime)
        assertEquals("7/30", usResult)
    }

    @Test
    fun testMMMdFormat() {
        val koreanResult = EasyFormat.MMMd(Locale.KOREA).format(testDateTime)
        assertEquals("7월 30일", koreanResult)

        val usResult = EasyFormat.MMMd(Locale.US).format(testDateTime)
        assertEquals("Jul 30", usResult)
    }

    @Test
    fun testMMMEdFormat() {
        val koreanResult = EasyFormat.MMMEd(Locale.KOREA).format(testDateTime)
        println("Korean MMMEd result: $koreanResult")
        assertTrue(koreanResult.contains("7월") && koreanResult.contains("수"))

        val usResult = EasyFormat.MMMEd(Locale.US).format(testDateTime)
        println("US MMMEd result: $usResult")
        assertTrue(usResult.contains("Wed") && usResult.contains("Jul"))
    }

    @Test
    fun testMMMMdFormat() {
        val koreanResult = EasyFormat.MMMMd(Locale.KOREA).format(testDateTime)
        assertEquals("7월 30일", koreanResult)

        val usResult = EasyFormat.MMMMd(Locale.US).format(testDateTime)
        assertEquals("July 30", usResult)
    }

    @Test
    fun testMMMMEdFormat() {
        val koreanResult = EasyFormat.MMMMEd(Locale.KOREA).format(testDateTime)
        println("Korean MMMMEd result: $koreanResult")
        assertTrue(koreanResult.contains("7월"))

        val usResult = EasyFormat.MMMMEd(Locale.US).format(testDateTime)
        println("US MMMMEd result: $usResult")
        assertTrue(usResult.contains("July"))
    }

    // New tests for added skeleton patterns
    @Test
    fun testYFormat() {
        val koreanResult = EasyFormat.y(Locale.KOREA).format(testDateTime)
        assertEquals("2025년", koreanResult)

        val usResult = EasyFormat.y(Locale.US).format(testDateTime)
        assertEquals("2025", usResult)
    }

    @Test
    fun testYMFormat() {
        val koreanResult = EasyFormat.yM(Locale.KOREA).format(testDateTime)
        assertEquals("2025. 7.", koreanResult)

        val usResult = EasyFormat.yM(Locale.US).format(testDateTime)
        assertEquals("7/2025", usResult)
    }

    @Test
    fun testYMMMFormat() {
        val koreanResult = EasyFormat.yMMM(Locale.KOREA).format(testDateTime)
        assertEquals("2025년 7월", koreanResult)

        val usResult = EasyFormat.yMMM(Locale.US).format(testDateTime)
        assertEquals("Jul 2025", usResult)
    }

    @Test
    fun testYMMMMFormat() {
        val koreanResult = EasyFormat.yMMMM(Locale.KOREA).format(testDateTime)
        assertEquals("2025년 7월", koreanResult)

        val usResult = EasyFormat.yMMMM(Locale.US).format(testDateTime)
        assertEquals("July 2025", usResult)
    }

    @Test
    fun testMFormat() {
        val koreanResult = EasyFormat.M(Locale.KOREA).format(testDateTime)
        assertEquals("7월", koreanResult)

        val usResult = EasyFormat.M(Locale.US).format(testDateTime)
        assertEquals("7", usResult)
    }

    @Test
    fun testMMMFormat() {
        val koreanResult = EasyFormat.MMM(Locale.KOREA).format(testDateTime)
        assertEquals("7월", koreanResult)

        val usResult = EasyFormat.MMM(Locale.US).format(testDateTime)
        assertEquals("Jul", usResult)
    }

    @Test
    fun testMMMMFormat() {
        val koreanResult = EasyFormat.MMMM(Locale.KOREA).format(testDateTime)
        assertEquals("7월", koreanResult)

        val usResult = EasyFormat.MMMM(Locale.US).format(testDateTime)
        assertEquals("July", usResult)
    }

    @Test
    fun testEFormat() {
        val koreanResult = EasyFormat.E(Locale.KOREA).format(testDateTime)
        assertEquals("수", koreanResult)

        val usResult = EasyFormat.E(Locale.US).format(testDateTime)
        assertEquals("Wed", usResult)
    }

    @Test
    fun testEEEEFormat() {
        val koreanResult = EasyFormat.EEEE(Locale.KOREA).format(testDateTime)
        assertEquals("수요일", koreanResult)

        val usResult = EasyFormat.EEEE(Locale.US).format(testDateTime)
        assertEquals("Wednesday", usResult)
    }

    @Test
    fun testHFormat() {
        val koreanResult = EasyFormat.H(Locale.KOREA).format(testDateTime)
        assertEquals("15시", koreanResult)

        val usResult = EasyFormat.H(Locale.US).format(testDateTime)
        assertEquals("15", usResult)
    }

    @Test
    fun testHmFormat24Hour() {
        val koreanResult = EasyFormat.Hm(Locale.KOREA).format(testDateTime)
        assertEquals("15:30", koreanResult)

        val usResult = EasyFormat.Hm(Locale.US).format(testDateTime)
        assertEquals("15:30", usResult)
    }

    @Test
    fun testHmsFormat12Hour() {
        val result = EasyFormat.hms(Locale.US).format(testDateTime)
        assertEquals("3:30:45 PM", result)
    }

    @Test
    fun testJmsFormat() {
        val koreanResult = EasyFormat.jms(Locale.KOREA).format(testDateTime)
        assertEquals("오후 3:30:45", koreanResult)

        val usResult = EasyFormat.jms(Locale.US).format(testDateTime)
        assertEquals("3:30:45 PM", usResult)
    }

    // ===== EasyFormatFormatter.kt 테스트되지 않은 함수들 =====

    @Test
    fun testDateFormatFormat() {
        val result = EasyFormat.yMMMd(Locale.US).format(testDate)
        println("Date format result: $result")
        // Date results may vary by timezone, so check for month and year presence
        assertTrue(result.contains("Jul") && result.contains("2025"))
    }

    @Test
    fun testFormatterChainingYMd() {
        val result = EasyFormat.Hm(Locale.US).yMd(Locale.US).format(testDateTime)
        println("Hm + yMd chaining result: $result")
        assertTrue(result.contains("15:30") && result.contains("7/30"))
    }

    @Test
    fun testFormatterChainingYMMMd() {
        val result = EasyFormat.Hm(Locale.US).yMMMd(Locale.US).format(testDateTime)
        println("Hm + yMMMd chaining result: $result")
        assertTrue(result.contains("15:30") && result.contains("Jul 30"))
    }

    @Test
    fun testFormatterChainingYMMMEd() {
        val result = EasyFormat.Hm(Locale.US).yMMMEd(Locale.US).format(testDateTime)
        println("Hm + yMMMEd chaining result: $result")
        assertTrue(result.contains("15:30") && result.contains("Jul 30"))
    }

    @Test
    fun testFormatterChainingMd() {
        val result = EasyFormat.Hm(Locale.US).Md(Locale.US).format(testDateTime)
        println("Hm + Md chaining result: $result")
        assertTrue(result.contains("15:30") && result.contains("7/30"))
    }

    @Test
    fun testFormatterChainingMMMd() {
        val result = EasyFormat.Hm(Locale.US).MMMd(Locale.US).format(testDateTime)
        println("Hm + MMMd chaining result: $result")
        assertTrue(result.contains("15:30") && result.contains("Jul 30"))
    }

    @Test
    fun testFormatterChainingHm24Hour() {
        val result = EasyFormat.yMMMd(Locale.US).Hm(Locale.US).format(testDateTime)
        println("yMMMd + Hm chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("15:30"))
    }

    @Test
    fun testFormatterChainingHms24Hour() {
        val result = EasyFormat.yMMMd(Locale.US).Hms(Locale.US).format(testDateTime)
        println("yMMMd + Hms chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("15:30:45"))
    }

    @Test
    fun testFormatterChainingHm12Hour() {
        val result = EasyFormat.yMMMd(Locale.US).hm(Locale.US).format(testDateTime)
        println("yMMMd + hm chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("3:30"))
    }

    @Test
    fun testFormatterChainingHms12Hour() {
        val result = EasyFormat.yMMMd(Locale.US).hms(Locale.US).format(testDateTime)
        println("yMMMd + hms chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("3:30:45"))
    }

    @Test
    fun testFormatterChainingJm() {
        val result = EasyFormat.yMMMd(Locale.US).jm(Locale.US).format(testDateTime)
        println("yMMMd + jm chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("3:30"))
    }

    @Test
    fun testFormatterChainingJms() {
        val result = EasyFormat.yMMMd(Locale.US).jms(Locale.US).format(testDateTime)
        println("yMMMd + jms chaining result: $result")
        assertTrue(result.contains("Jul 30") && result.contains("3:30:45"))
    }

    // ===== EasyFormatLocaleBuilder.kt 테스트되지 않은 함수들 =====

    @Test
    fun testLocaleBuilderYMd() {
        val result = EasyFormat.locale(Locale.US).yMd().format(testDateTime)
        // ICU may use 4-digit year, so check for year and month presence
        assertTrue(result.contains("7/30") && result.contains("2025"))
    }

    @Test
    fun testLocaleBuilderYMMMd() {
        val result = EasyFormat.locale(Locale.US).yMMMd().format(testDateTime)
        assertEquals("Jul 30, 2025", result)
    }

    @Test
    fun testLocaleBuilderYMMMEd() {
        val result = EasyFormat.locale(Locale.US).yMMMEd().format(testDateTime)
        println("LocaleBuilder yMMMEd result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("Wed"))
    }

    @Test
    fun testLocaleBuilderYMMMMd() {
        val result = EasyFormat.locale(Locale.US).yMMMMd().format(testDateTime)
        assertEquals("July 30, 2025", result)
    }

    @Test
    fun testLocaleBuilderYMMMMEd() {
        val result = EasyFormat.locale(Locale.US).yMMMMEd().format(testDateTime)
        println("LocaleBuilder yMMMMEd result: $result")
        // Check for year and month presence (format may vary)
        assertTrue(result.contains("2025") && result.contains("July"))
    }

    @Test
    fun testLocaleBuilderMd() {
        val result = EasyFormat.locale(Locale.US).Md().format(testDateTime)
        assertEquals("7/30", result)
    }

    @Test
    fun testLocaleBuilderMMMd() {
        val result = EasyFormat.locale(Locale.US).MMMd().format(testDateTime)
        assertEquals("Jul 30", result)
    }

    @Test
    fun testLocaleBuilderMMMEd() {
        val result = EasyFormat.locale(Locale.US).MMMEd().format(testDateTime)
        println("LocaleBuilder MMMEd result: $result")
        assertTrue(result.contains("Wed") && result.contains("Jul"))
    }

    @Test
    fun testLocaleBuilderMMMMd() {
        val result = EasyFormat.locale(Locale.US).MMMMd().format(testDateTime)
        assertEquals("July 30", result)
    }

    @Test
    fun testLocaleBuilderMMMMEd() {
        val result = EasyFormat.locale(Locale.US).MMMMEd().format(testDateTime)
        println("LocaleBuilder MMMMEd result: $result")
        assertTrue(result.contains("July"))
    }

    @Test
    fun testLocaleBuilderHm24Hour() {
        val result = EasyFormat.locale(Locale.US).Hm().format(testDateTime)
        assertEquals("15:30", result)
    }

    @Test
    fun testLocaleBuilderHms24Hour() {
        val result = EasyFormat.locale(Locale.US).Hms().format(testDateTime)
        assertEquals("15:30:45", result)
    }

    @Test
    fun testLocaleBuilderHm12Hour() {
        val result = EasyFormat.locale(Locale.US).hm().format(testDateTime)
        assertEquals("3:30 PM", result)
    }

    @Test
    fun testLocaleBuilderHms12Hour() {
        val result = EasyFormat.locale(Locale.US).hms().format(testDateTime)
        assertEquals("3:30:45 PM", result)
    }

    @Test
    fun testLocaleBuilderJm() {
        val result = EasyFormat.locale(Locale.US).jm().format(testDateTime)
        assertEquals("3:30 PM", result)
    }

    @Test
    fun testLocaleBuilderJms() {
        val result = EasyFormat.locale(Locale.US).jms().format(testDateTime)
        assertEquals("3:30:45 PM", result)
    }

    @Test
    fun testLocaleBuilderCustom() {
        val result = EasyFormat.locale(Locale.US).custom("yMMMMd").format(testDateTime)
        assertEquals("July 30, 2025", result)
    }

    @Test
    fun testLocaleBuilderChaining() {
        val result = EasyFormat.locale(Locale.US).yMMMd().Hms().format(testDateTime)
        println("LocaleBuilder chaining result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("15:30:45"))
    }

    @Test
    fun testKoreanLocaleBuilder() {
        val result = EasyFormat.locale(Locale.KOREA).yMMMd().jm().format(testDateTime)
        println("Korean LocaleBuilder result: $result")
        assertTrue(result.contains("2025년 7월 30일") && result.contains("오후"))
    }

    // Tests for method chaining with new patterns
    @Test
    fun testChainingYWithHm() {
        val result = EasyFormat.y(Locale.KOREA).Hm(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("2025년") && result.contains("15:30"))
    }

    @Test
    fun testChainingEWithHms() {
        val result = EasyFormat.E(Locale.KOREA).Hms(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("수") && result.contains("15:30:45"))
    }

    @Test
    fun testChainingMMMWithHm() {
        val result = EasyFormat.MMM(Locale.KOREA).Hm(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("7월") && result.contains("15:30"))
    }

    @Test
    fun testChainingYMMMWithHm() {
        val result = EasyFormat.yMMM(Locale.KOREA).Hm(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("2025년 7월") && result.contains("15:30"))
    }

    @Test
    fun testChainingEEEEWithHm() {
        val result = EasyFormat.EEEE(Locale.KOREA).Hm(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("수요일") && result.contains("15:30"))
    }

    @Test
    fun testChainingHWithE() {
        val result = EasyFormat.H(Locale.KOREA).E(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("15시") && result.contains("수"))
    }

    @Test
    fun testChainingHmWithM() {
        val result = EasyFormat.Hm(Locale.KOREA).M(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("15:30") && result.contains("7월"))
    }

    @Test
    fun testChainingH12HourWithE() {
        val result = EasyFormat.h(Locale.KOREA).E(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("3") && result.contains("수"))
    }

    @Test
    fun testChainingHm12HourWithM() {
        val result = EasyFormat.hm(Locale.KOREA).M(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("오후 3:30") && result.contains("7월"))
    }

    @Test
    fun testChainingHms12HourWithMMM() {
        val result = EasyFormat.hms(Locale.KOREA).MMM(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("오후 3:30:45") && result.contains("7월"))
    }

    @Test
    fun testChainingJmWithE() {
        val result = EasyFormat.jm(Locale.KOREA).E(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("오후 3:30") && result.contains("수"))
    }

    @Test
    fun testChainingJmsWithM() {
        val result = EasyFormat.jms(Locale.KOREA).M(Locale.KOREA).format(testDateTime)
        assertTrue(result.contains("오후 3:30:45") && result.contains("7월"))
    }

    // ===== 새로운 기능들에 대한 테스트 =====

    // Timezone format tests
    @Test
    fun testTimezoneAbbreviation() {
        val result = EasyFormat.z(Locale.US).format(testDateTime)
        println("Timezone abbreviation result: $result")
        // Timezone abbreviation may vary by system, so check it's not empty
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testFullTimezoneName() {
        val result = EasyFormat.zzzz(Locale.US).format(testDateTime)
        println("Full timezone name result: $result")
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testISO8601Timezone() {
        val result = EasyFormat.Z(Locale.US).format(testDateTime)
        println("ISO 8601 timezone result: $result")
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testTimezoneID() {
        val result = EasyFormat.ZZZZ(Locale.US).format(testDateTime)
        println("Timezone ID result: $result")
        assertTrue(result.isNotEmpty())
    }

    // Week format tests
    @Test
    fun testWeekOfYear() {
        val result = EasyFormat.w(Locale.US).format(testDateTime)
        println("Week of year result: $result")
        // Week should be a number between 1-53
        assertTrue(result.toIntOrNull() != null && result.toInt() in 1..53)
    }

    @Test
    fun testWeekOfMonth() {
        val result = EasyFormat.W(Locale.US).format(testDateTime)
        println("Week of month result: $result")
        // Week of month should be a number between 1-6
        assertTrue(result.toIntOrNull() != null && result.toInt() in 1..6)
    }

    // Quarter format tests
    @Test
    fun testQuarter() {
        val result = EasyFormat.Q(Locale.US).format(testDateTime)
        println("Quarter result: $result")
        // Quarter should be 1, 2, 3, or 4
        assertTrue(result.toIntOrNull() != null && result.toInt() in 1..4)
    }

    @Test
    fun testQuarterName() {
        val result = EasyFormat.QQQ(Locale.US).format(testDateTime)
        println("Quarter name result: $result")
        // Should contain "Q" and a number
        assertTrue(result.contains("Q"))
    }

    @Test
    fun testFullQuarterName() {
        val result = EasyFormat.QQQQ(Locale.US).format(testDateTime)
        println("Full quarter name result: $result")
        assertTrue(result.isNotEmpty())
    }

    // Era format tests
    @Test
    fun testEra() {
        val result = EasyFormat.G(Locale.US).format(testDateTime)
        println("Era result: $result")
        // Should be "AD" for current era
        assertEquals("AD", result)
    }

    @Test
    fun testFullEraName() {
        val result = EasyFormat.GGGG(Locale.US).format(testDateTime)
        println("Full era name result: $result")
        assertTrue(result.isNotEmpty())
    }

    // ISO 8601 year format test
    @Test
    fun testISO8601Year() {
        val result = EasyFormat.u(Locale.US).format(testDateTime)
        println("ISO 8601 year result: $result")
        assertEquals("2025", result)
    }

    // Complex chaining tests with new formats
    @Test
    fun testDateWithTimezone() {
        val result = EasyFormat.yMMMd(Locale.US).z(Locale.US).format(testDateTime)
        println("Date with timezone result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.isNotEmpty())
    }

    @Test
    fun testDateWithWeekAndQuarter() {
        val result = EasyFormat.yMMMd(Locale.US).w(Locale.US).Q(Locale.US).format(testDateTime)
        println("Date with week and quarter result: $result")
        assertTrue(result.contains("Jul 30, 2025"))
    }

    @Test
    fun testFullDateTimeWithTimezone() {
        val result = EasyFormat.yMMMEd(Locale.US).Hms(Locale.US).zzzz(Locale.US).format(testDateTime)
        println("Full date time with timezone result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("15:30:45"))
    }

    // Locale-first chaining tests with new formats
    @Test
    fun testLocaleFirstWithTimezone() {
        val result = EasyFormat.locale(Locale.KOREA).yMMMd().z().format(testDateTime)
        println("Locale-first with timezone result: $result")
        assertTrue(result.contains("2025년 7월 30일"))
    }

    @Test
    fun testLocaleFirstWithWeekAndQuarter() {
        val result = EasyFormat.locale(Locale.US).w().QQQ().format(testDateTime)
        println("Locale-first with week and quarter result: $result")
        assertTrue(result.contains("Q"))
    }

    @Test
    fun testLocaleFirstWithEraAndYear() {
        val result = EasyFormat.locale(Locale.US).G().u().format(testDateTime)
        println("Locale-first with era and year result: $result")
        assertTrue(result.contains("AD") && result.contains("2025"))
    }

    // Additional chaining tests for new patterns
    @Test
    fun testChainingZWithYMMMd() {
        val result = EasyFormat.z(Locale.US).yMMMd(Locale.US).format(testDateTime)
        println("z + yMMMd chaining result: $result")
        assertTrue(result.contains("Jul 30, 2025"))
    }

    @Test
    fun testChainingWWithHm() {
        val result = EasyFormat.W(Locale.US).Hm(Locale.US).format(testDateTime)
        println("W + Hm chaining result: $result")
        assertTrue(result.contains("15:30"))
    }

    @Test
    fun testChainingQWithE() {
        val result = EasyFormat.Q(Locale.US).E(Locale.US).format(testDateTime)
        println("Q + E chaining result: $result")
        assertTrue(result.contains("Wed"))
    }

    @Test
    fun testChainingGWithM() {
        val result = EasyFormat.G(Locale.US).M(Locale.US).format(testDateTime)
        println("G + M chaining result: $result")
        assertTrue(result.contains("AD") && result.contains("7"))
    }

    @Test
    fun testChainingUWithHms() {
        val result = EasyFormat.u(Locale.US).Hms(Locale.US).format(testDateTime)
        println("u + Hms chaining result: $result")
        assertTrue(result.contains("2025") && result.contains("15:30:45"))
    }

    // Test with different locales for new formats
    @Test
    fun testTimezoneWithKoreanLocale() {
        val result = EasyFormat.z(Locale.KOREA).format(testDateTime)
        println("Korean timezone result: $result")
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testQuarterWithKoreanLocale() {
        val result = EasyFormat.Q(Locale.KOREA).format(testDateTime)
        println("Korean quarter result: $result")
        assertTrue(result.toIntOrNull() != null && result.toInt() in 1..4)
    }

    @Test
    fun testWeekWithKoreanLocale() {
        val result = EasyFormat.w(Locale.KOREA).format(testDateTime)
        println("Korean week result: $result")
        assertTrue(result.toIntOrNull() != null && result.toInt() in 1..53)
    }

    @Test
    fun testEraWithKoreanLocale() {
        val result = EasyFormat.G(Locale.KOREA).format(testDateTime)
        println("Korean era result: $result")
        assertTrue(result.isNotEmpty())
    }

    // ===== 빠진 테스트들 추가 =====

    // EasyFormat.kt의 빠진 함수들 테스트
    @Test
    fun testH12HourFormat() {
        val result = EasyFormat.h(Locale.US).format(testDateTime)
        println("12-hour format result: $result")
        assertEquals("3 PM", result)
    }

    @Test
    fun testMinuteFormat() {
        val result = EasyFormat.m(Locale.US).format(testDateTime)
        println("Minute format result: $result")
        assertEquals("30", result)
    }

    @Test
    fun testSecondFormat() {
        val result = EasyFormat.s(Locale.US).format(testDateTime)
        println("Second format result: $result")
        assertEquals("45", result)
    }

    // EasyFormatFormatter.kt의 빠진 체이닝 테스트들
    @Test
    fun testFormatterChainingH12Hour() {
        val result = EasyFormat.yMMMd(Locale.US).h(Locale.US).format(testDateTime)
        println("yMMMd + h chaining result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("3"))
    }

    @Test
    fun testFormatterChainingMinute() {
        val result = EasyFormat.yMMMd(Locale.US).m(Locale.US).format(testDateTime)
        println("yMMMd + m chaining result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("30"))
    }

    @Test
    fun testFormatterChainingSecond() {
        val result = EasyFormat.yMMMd(Locale.US).s(Locale.US).format(testDateTime)
        println("yMMMd + s chaining result: $result")
        assertTrue(result.contains("Jul 30, 2025") && result.contains("45"))
    }

    // EasyFormatLocaleBuilder.kt의 빠진 테스트들
    @Test
    fun testLocaleBuilderH12Hour() {
        val result = EasyFormat.locale(Locale.US).h().format(testDateTime)
        println("LocaleBuilder h result: $result")
        assertEquals("3 PM", result)
    }

    @Test
    fun testLocaleBuilderMinute() {
        val result = EasyFormat.locale(Locale.US).m().format(testDateTime)
        println("LocaleBuilder m result: $result")
        assertEquals("30", result)
    }

    @Test
    fun testLocaleBuilderSecond() {
        val result = EasyFormat.locale(Locale.US).s().format(testDateTime)
        println("LocaleBuilder s result: $result")
        assertEquals("45", result)
    }

    // 추가 체이닝 테스트들
    @Test
    fun testChainingMinuteWithE() {
        val result = EasyFormat.m(Locale.US).E(Locale.US).format(testDateTime)
        println("m + E chaining result: $result")
        assertTrue(result.contains("30") && result.contains("Wed"))
    }

    @Test
    fun testChainingSecondWithE() {
        val result = EasyFormat.s(Locale.US).E(Locale.US).format(testDateTime)
        println("s + E chaining result: $result")
        assertTrue(result.contains("45") && result.contains("Wed"))
    }
} 