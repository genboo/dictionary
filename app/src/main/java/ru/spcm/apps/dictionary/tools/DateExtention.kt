package ru.spcm.apps.dictionary.tools

import java.text.SimpleDateFormat
import java.util.*

/**
 * Форматирование даны по шаблону
 */
fun Date.format(pattern: String = "dd.MM.yyyy"): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(this)
}

/**
 * Парсинг даны по шаблону
 */
fun String.parse(pattern: String = "yyyy-MM-dd"): Date {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)
}