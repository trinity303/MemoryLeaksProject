package com.example.memory.leaks.project.common.data.logger

import android.content.Context
import java.io.File

/**
 * @author kebirova@kolesa.kz
 */
data class LoggerState(
        val context: Context,
        val file: File
)