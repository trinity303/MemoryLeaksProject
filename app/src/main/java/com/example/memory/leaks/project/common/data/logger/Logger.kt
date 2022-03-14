package com.example.memory.leaks.project.common.data.logger

import android.util.Log

/**
 * @author kebirova@kolesa.kz
 */

class Logger private constructor(
        private val sState: LoggerState
) {

    fun log() {
        Log.d(sState.context.applicationInfo.toString(), sState.file.absolutePath)
    }

    companion object {
        private var sInstance: Logger? = null

        fun createInstance(state: LoggerState) {
            sInstance = Logger(state)
        }

        val instance: Logger
            get() = if (sInstance == null) {
                throw IllegalArgumentException("User cannot be null")
            } else {
                sInstance!!
            }
    }
}