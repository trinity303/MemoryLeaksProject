package com.example.memory.leaks.project.sender.domain

import androidx.annotation.WorkerThread
import com.example.memory.leaks.project.common.data.Response

/**
 * @author kebirova@kolesa.kz
 */
class DetailsSenderUseCase(
        private val repository: DetailsSenderRepository
) {

    @WorkerThread
    fun execute(presenter: DetailsSenderPresenter) {
        when (repository.send()) {
            is Response.Success -> presenter.successSent()
            is Response.Error -> presenter.failureSent()
        }
    }
}