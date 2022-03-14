package com.example.memory.leaks.project.main.presentation

import android.content.Context
import android.os.Environment
import com.example.memory.leaks.project.common.data.logger.Logger
import com.example.memory.leaks.project.common.data.logger.LoggerState
import com.example.memory.leaks.project.common.wrapper.ResourceWrapper
import com.example.memory.leaks.project.common.wrapper.DefaultResourceWrapper
import com.example.memory.leaks.project.common.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

/**
 * @author kebirova@kolesa.kz
 */
class MainPresenter(
        private val viewListener: MainContract.View
): MainContract.Presenter {

    private var randomResult = 0

    init {
        createUser()
    }

    override fun onStart() {
        initLogger()
    }

    override fun onCalculateClicked() {
        viewListener.showProgress()
        GlobalScope.launch(Dispatchers.IO) {
            delay(3000)
            randomResult = Random().nextInt(20)

            launch(Dispatchers.Main) {
                viewListener.hideProgress()
                viewListener.showCalculatedResult(randomResult)
            }
        }
    }


    private fun createUser() {
        val resourceWrapper: ResourceWrapper = DefaultResourceWrapper(viewListener as Context)
        User.createInstance(resourceWrapper)
    }

    private fun initLogger() {
        val initLogger = InitLogger()
        val loggerState = LoggerState(viewListener as Context, File(getPath()))
        initLogger.initialize(loggerState)
    }

    private fun getPath(): String = Environment.getExternalStorageDirectory().absolutePath

    inner class InitLogger {
        fun initialize(state: LoggerState) {
            GlobalScope.launch(Dispatchers.IO) {
                delay(3000)
                Logger.createInstance(state)
                Logger.instance.log()

                launch(Dispatchers.Main) {
                    viewListener.showSuccessLoggerInitMessage()
                }
            }
        }
    }
}