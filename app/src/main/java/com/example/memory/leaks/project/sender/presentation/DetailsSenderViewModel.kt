package com.example.memory.leaks.project.sender.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.memory.leaks.project.sender.domain.DetailsSenderPresenter
import com.example.memory.leaks.project.sender.domain.DetailsSenderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author kebirova@kolesa.kz
 */
class DetailsSenderViewModel(
        private val useCase: DetailsSenderUseCase
): ViewModel(), DetailsSenderPresenter {

    fun onSendClicked() {
        GlobalScope.launch(Dispatchers.IO) {
            useCase.execute(this@DetailsSenderViewModel)
        }
    }

    override fun successSent() {
        Log.d("DetailsSenderViewModel", "success sent details")
    }

    override fun failureSent() {
        Log.d("DetailsSenderViewModel", "failure sent details")
    }
}