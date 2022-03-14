package com.example.memory.leaks.project.sender.domain

import androidx.annotation.WorkerThread
import com.example.memory.leaks.project.common.data.Response
import java.lang.Exception

/**
 * @author kebirova@kolesa.kz
 */
interface DetailsSenderRepository {

    @WorkerThread
    fun send(): Response<Boolean, Exception>
}