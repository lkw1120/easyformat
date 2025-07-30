# EasyFormat

A Kotlin-based date/time formatting library for Android that provides locale-aware formatting using ICU skeletons with an intuitive API similar to Flutter's intl.DateFormat.

## Features

- **ICU skeleton support**: Uses Android's built-in ICU for date/time formatting
- **Method chaining**: Chain multiple format methods together
- **Locale support**: Format dates and times according to locale preferences
- **Multiple data types**: Format `Instant`, `LocalDateTime`, and `Date` objects
- **Various formats**: Date, time, timezone, week, quarter, and era formatting
- **Custom patterns**: Use custom ICU skeleton patterns

## Installation

Add JitPack repository to your project's `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        // ... other repositories
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.lkw1120:easyformat:1.0.0")
}
```

## Quick Start

```kotlin
val now = LocalDateTime.now()

// Basic formatting
val dateStr = EasyFormat.yMMMd(Locale.US).format(now)        // "Jul 30, 2025"
val timeStr = EasyFormat.Hms(Locale.US).format(now)          // "14:30:25"

// Method chaining
val fullDateTime = EasyFormat.yMMMd(Locale.US).Hms().format(now)  // "Jul 30, 2025 14:30:25"

// Locale-first chaining
val localized = EasyFormat.locale(Locale.US).yMMMd().Hms().format(now)  // "Jul 30, 2025 14:30:25"
```

## API Reference

### Date Formats

| Method | Description |
|--------|-------------|
| `y()` | Year |
| `yM()` | Year-Month |
| `yMMM()` | Year-Month with month name |
| `yMMMM()` | Year-Month with full month name |
| `yMd()` | Year-Month-Day |
| `yMMMd()` | Year-Month-Day with month name |
| `yMMMEd()` | Year-Month-Day-Weekday |
| `yMMMMd()` | Year-Month-Day with full month name |
| `yMMMMEd()` | Year-Month-Day-Weekday with full month name |

### Time Formats

| Method | Description |
|--------|-------------|
| `H()` | Hour (24-hour) |
| `Hm()` | Hour:Minute (24-hour) |
| `Hms()` | Hour:Minute:Second (24-hour) |
| `h()` | Hour (12-hour) |
| `hm()` | Hour:Minute (12-hour) |
| `hms()` | Hour:Minute:Second (12-hour) |
| `jm()` | Hour:Minute (locale-specific) |
| `jms()` | Hour:Minute:Second (locale-specific) |
| `Jm()` | Hour:Minute (locale-specific, no AM/PM) |
| `Jms()` | Hour:Minute:Second (locale-specific, no AM/PM) |

### Individual Components

| Method | Description |
|--------|-------------|
| `M()`, `MMM()`, `MMMM()` | Month formats |
| `d()` | Day of month |
| `E()`, `EEEE()` | Weekday formats |
| `m()`, `s()` | Minute, second |
| `z()`, `zzzz()`, `Z()`, `ZZZZ()` | Timezone formats |
| `w()`, `W()` | Week formats |
| `Q()`, `QQQ()`, `QQQQ()` | Quarter formats |
| `G()`, `GGGG()` | Era formats |
| `u()` | ISO 8601 year |

### Method Chaining

Combine multiple formats using method chaining:

```kotlin
// Date + Time
EasyFormat.yMMMd(Locale.US).Hms().format(now)  // "Jul 30, 2025 14:30:25"

// Time + Date
EasyFormat.Hms(Locale.US).yMMMd().format(now)  // "14:30:25 Jul 30, 2025"

// Complex combinations
EasyFormat.yMMMEd(Locale.US).Hms().z().format(now)  // "Wed, Jul 30, 2025 14:30:25 PDT"
```

### Locale-First Chaining

Set locale once and chain methods:

```kotlin
// Set locale first
val formatter = EasyFormat.locale(Locale.US).yMMMd().Hms()
val result = formatter.format(now)  // "Jul 30, 2025 14:30:25"

// Custom skeleton
val custom = EasyFormat.locale(Locale.US).custom("yMMMMd").format(now)  // "July 30, 2025"
```

### Custom Skeletons

Use custom ICU skeleton patterns:

```kotlin
// Custom date format
EasyFormat.custom("yMd", Locale.US).format(now)  // "7/30/2025"

// Custom time format
EasyFormat.custom("jm", Locale.US).format(now)   // "2:30 PM"
```

## Supported Data Types

EasyFormat supports multiple date/time types:

```kotlin
val instant = Instant.now()
val localDateTime = LocalDateTime.now()
val date = Date()

// All work the same way
EasyFormat.yMMMd(Locale.US).format(instant)
EasyFormat.yMMMd(Locale.US).format(localDateTime)
EasyFormat.yMMMd(Locale.US).format(date)
```

## Locale Support

EasyFormat leverages Android's built-in ICU library, which provides comprehensive locale support for all standard locales. The sample app demonstrates formatting with various locales for testing purposes.

## Requirements

- Android API 26+ (Android 8.0)
- Java 8+

## Sample App

Check out the [sample app](https://github.com/lkw1120/EasyFormat/tree/main/sample) for comprehensive examples of all formatting options and locale support.

## Contributing

Contributions are welcome! Feel free to submit issues and pull requests.

## License

```
Copyright 2025 lkw1120

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
