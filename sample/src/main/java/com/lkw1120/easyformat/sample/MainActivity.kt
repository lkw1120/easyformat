package com.lkw1120.easyformat.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lkw1120.easyformat.EasyFormat
import com.lkw1120.easyformat.sample.theme.SampleTheme
import java.time.Instant
import java.time.LocalDateTime
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                EasyFormatApp()
            }
        }
    }
}

fun safeLocale(language: String, region: String): Locale =
    Locale.Builder().setLanguage(language).setRegion(region).build()

@SuppressWarnings("deprecation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasyFormatApp() {
    var selectedLocale by remember { mutableStateOf(Locale.getDefault()) }
    var showLocaleMenu by remember { mutableStateOf(false) }

    val locales = listOf(
        Locale.US to "English (US)",
        Locale.UK to "English (UK)",
        Locale.KOREA to "한국어",
        Locale.JAPAN to "日本語",
        Locale.CHINESE to "中文",
        Locale.GERMAN to "Deutsch",
        Locale.FRENCH to "Français",
        Locale.ITALIAN to "Italiano",
        safeLocale("es", "ES") to "Español",
        safeLocale("pt", "PT") to "Português",
        safeLocale("nl", "NL") to "Nederlands",
        safeLocale("cs", "CZ") to "Čeština",
        safeLocale("el", "GR") to "Ελληνικά",
        safeLocale("sv", "SE") to "Svenska",
        safeLocale("pl", "PL") to "Polski",
        safeLocale("tr", "TR") to "Türkçe",
        safeLocale("id", "ID") to "Bahasa Indonesia",
        safeLocale("he", "IL") to "עברית",
        safeLocale("ru", "RU") to "Русский",
        safeLocale("ar", "SA") to "العربية",
        safeLocale("hi", "IN") to "हिन्दी",
        safeLocale("th", "TH") to "ไทย",
        safeLocale("vi", "VN") to "Tiếng Việt",
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("EasyFormat Sample") },
                actions = {
                    Box {
                        IconButton(onClick = { showLocaleMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Select Locale"
                            )
                        }
                        DropdownMenu(
                            expanded = showLocaleMenu,
                            onDismissRequest = { showLocaleMenu = false }
                        ) {
                            locales.forEach { (locale, displayName) ->
                                DropdownMenuItem(
                                    text = { Text(displayName) },
                                    onClick = {
                                        selectedLocale = locale
                                        showLocaleMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        EasyFormatDemo(
            selectedLocale = selectedLocale,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun EasyFormatDemo(
    selectedLocale: Locale = Locale.US,
    modifier: Modifier = Modifier
) {
    val now = LocalDateTime.now()
    val instant = Instant.now()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "EasyFormat Sample App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "==================",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Current Locale Display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Selected Locale: ${selectedLocale.displayName} (${selectedLocale.language})",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // 1. Basic Date Formatting
        DemoSection("1. Basic Date Formatting:") {
            DemoItem("yMMMd", EasyFormat.yMMMd(selectedLocale).format(now))
        }
        
        // 2. Date with Weekday
        DemoSection("2. Date with Weekday:") {
            DemoItem("yMMMEd", EasyFormat.yMMMEd(selectedLocale).format(now))
        }
        
        // 3. Time Formatting
        DemoSection("3. Time Formatting:") {
            DemoItem("24h", EasyFormat.Hm(selectedLocale).format(now))
            DemoItem("24h+seconds", EasyFormat.Hms(selectedLocale).format(now))
            DemoItem("12h", EasyFormat.hm(selectedLocale).format(now))
            DemoItem("12h+seconds", EasyFormat.hms(selectedLocale).format(now))
            DemoItem("Locale-specific", EasyFormat.jm(selectedLocale).format(now))
            DemoItem("Locale-specific+seconds", EasyFormat.jms(selectedLocale).format(now))
        }
        
        // 4. Instant Formatting
        DemoSection("4. Instant Formatting:") {
            DemoItem("Date", EasyFormat.yMMMd(selectedLocale).format(instant))
            DemoItem("Time+seconds", EasyFormat.Hms(selectedLocale).format(instant))
        }
        
        // 5. Method Chaining
        DemoSection("5. Method Chaining:") {
            DemoItem("Date+Time+Seconds", EasyFormat.yMMMd(selectedLocale).Hms(selectedLocale).format(now))
            DemoItem("Time+Seconds+Date", EasyFormat.Hms(selectedLocale).yMMMd(selectedLocale).format(now))
        }
        
        // 6. Method Chaining with Default Locale
        DemoSection("6. Method Chaining with Default Locale:") {
            DemoItem("Date+Time+Seconds", EasyFormat.yMMMd(selectedLocale).Hms().format(now))
            DemoItem("Date+Weekday", EasyFormat.yMMMd(selectedLocale).E().format(now))
        }
        
        // 7. Locale-First Chaining
        DemoSection("7. Locale-First Chaining:") {
            DemoItem("Date+Time+Seconds", EasyFormat.locale(selectedLocale).yMMMd().Hms().format(now))
            DemoItem("Custom", EasyFormat.locale(selectedLocale).custom("yMMMMd").format(now))
        }
        
        // 8. Various Skeletons
        DemoSection("8. Various Skeletons:") {
            DemoItem("yMd", EasyFormat.custom("yMd", selectedLocale).format(now))
            DemoItem("MMMd", EasyFormat.custom("MMMd", selectedLocale).format(now))
            DemoItem("Locale-specific time", EasyFormat.custom("jm", selectedLocale).format(now))
        }
        
        // 9. Month and Day Formats
        DemoSection("9. Month and Day Formats:") {
            DemoItem("Month only", EasyFormat.MMM(selectedLocale).format(now))
            DemoItem("Month-Day", EasyFormat.MMMd(selectedLocale).format(now))
            DemoItem("Weekday", EasyFormat.EEEE(selectedLocale).format(now))
        }
        
        // 10. Individual Components
        DemoSection("10. Individual Components:") {
            DemoItem("Year only", EasyFormat.y(selectedLocale).format(now))
            DemoItem("Year-Month", EasyFormat.yM(selectedLocale).format(now))
            DemoItem("Year-Month Name", EasyFormat.yMMM(selectedLocale).format(now))
            DemoItem("Hour only", EasyFormat.h(selectedLocale).format(now))
            DemoItem("Minute only", EasyFormat.m(selectedLocale).format(now))
            DemoItem("Second only", EasyFormat.s(selectedLocale).format(now))
        }
        
        // 11. Complex Chaining Examples
        DemoSection("11. Complex Chaining Examples:") {
            DemoItem("Date+Weekday+Time+Seconds", EasyFormat.yMMMEd(selectedLocale).Hms().format(now))
            DemoItem("Date+Time+Zone", EasyFormat.yMMMd(selectedLocale).Hm().z().format(now))
        }
        
        // 12. Timezone Formats
        DemoSection("12. Timezone Formats:") {
            DemoItem("Abbreviation", EasyFormat.z(selectedLocale).format(now))
            DemoItem("Full Name", EasyFormat.zzzz(selectedLocale).format(now))
            DemoItem("ISO 8601", EasyFormat.Z(selectedLocale).format(now))
            DemoItem("Timezone ID", EasyFormat.ZZZZ(selectedLocale).format(now))
        }
        
        // 13. Week Formats
        DemoSection("13. Week Formats:") {
            DemoItem("Week of Year", EasyFormat.w(selectedLocale).format(now))
            DemoItem("Week of Month", EasyFormat.W(selectedLocale).format(now))
        }
        
        // 14. Quarter Formats
        DemoSection("14. Quarter Formats:") {
            DemoItem("Quarter", EasyFormat.Q(selectedLocale).format(now))
            DemoItem("Quarter Name", EasyFormat.QQQ(selectedLocale).format(now))
            DemoItem("Full Quarter", EasyFormat.QQQQ(selectedLocale).format(now))
        }
        
        // 15. Era Formats
        DemoSection("15. Era Formats:") {
            DemoItem("Era", EasyFormat.G(selectedLocale).format(now))
            DemoItem("Full Era", EasyFormat.GGGG(selectedLocale).format(now))
        }
        
        // 16. ISO 8601 Year
        DemoSection("16. ISO 8601 Year:") {
            DemoItem("ISO Year", EasyFormat.u(selectedLocale).format(now))
        }
        
        // 17. Complex Chaining with New Formats
        DemoSection("17. Complex Chaining with New Formats:") {
            DemoItem("Date+Time+Timezone", EasyFormat.yMMMd(selectedLocale).Hm().z().format(now))
            DemoItem("Date+Week+Quarter", EasyFormat.yMMMd(selectedLocale).w().Q().format(now))
            DemoItem("Full DateTime+Zone", EasyFormat.yMMMEd(selectedLocale).Hms().zzzz().format(now))
        }
        
        // 18. Locale-First with New Formats
        DemoSection("18. Locale-First with New Formats:") {
            DemoItem("Date+Timezone", EasyFormat.locale(selectedLocale).yMMMd().z().format(now))
            DemoItem("Week+Quarter", EasyFormat.locale(selectedLocale).w().QQQ().format(now))
            DemoItem("Era+Year", EasyFormat.locale(selectedLocale).G().u().format(now))
        }
    }
}

@Composable
fun DemoSection(title: String, content: @Composable () -> Unit) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
    content()
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun DemoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 2.dp, bottom = 2.dp)
    ) {
        Text(
            text = "$label: ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EasyFormatDemoPreview() {
    SampleTheme {
        EasyFormatApp()
    }
}