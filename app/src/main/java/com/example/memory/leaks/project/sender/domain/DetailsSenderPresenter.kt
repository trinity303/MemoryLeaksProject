package com.example.memory.leaks.project.sender.domain

import androidx.annotation.WorkerThread

/**
 * @author kebirova@kolesa.kz
 */
interface DetailsSenderPresenter {

    @WorkerThread
    fun successSent()

    @WorkerThread
    fun failureSent()
}