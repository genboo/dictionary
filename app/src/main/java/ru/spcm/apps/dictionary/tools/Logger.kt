package ru.spcm.apps.dictionary.tools

import android.util.Log
import ru.spcm.apps.dictionary.BuildConfig

/**
 * Логгер с набором методов для логирования с различным уровнем
 */
object Logger {

    private const val DEFAULT_LOGGER = BuildConfig.APPLICATION_ID

    fun e(tag: String, message: String?) {
        if (BuildConfig.DEBUG_MODE) {
            Log.e(tag, message)
        }
    }

    fun e(tag: String, message: Exception) {
        e(tag, message.message)
    }

    fun e(message: Exception) {
        e(DEFAULT_LOGGER, message.message ?: "")
    }

    fun e(message: String?) {
        e(DEFAULT_LOGGER, message)
    }

    fun d(tag: String, message: String?) {
        if (BuildConfig.DEBUG_MODE) {
            Log.d(tag, message)
        }
    }

    fun d(tag: String, message: Exception) {
        d(tag, message.message)
    }

    fun d(message: Exception) {
        d(DEFAULT_LOGGER, message.message)
    }

    fun d(message: String) {
        d(DEFAULT_LOGGER, message)
    }
}
