package com.example.memory.leaks.project.details.data

import androidx.annotation.WorkerThread
import com.example.memory.leaks.project.details.model.Details
import java.lang.Exception
import kotlin.jvm.Throws

class DetailsRepository {

    @WorkerThread
    @Throws(Exception::class)
    fun loadDetails(): Details {
        Thread.sleep(2000)

        return Details(1, "Kenobi")
    }
}