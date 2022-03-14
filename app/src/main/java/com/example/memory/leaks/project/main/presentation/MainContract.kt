package com.example.memory.leaks.project.main.presentation

/**
 * @author kebirova@kolesa.kz
 */
interface MainContract {

    interface Presenter: MainContract {

        fun onStart()

        fun onCalculateClicked()
    }

    interface View: MainContract {

        fun showSuccessLoggerInitMessage()

        fun showProgress()

        fun hideProgress()

        fun showCalculatedResult(result: Int)
    }
}