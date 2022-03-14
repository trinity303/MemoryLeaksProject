package com.example.memory.leaks.project.common.wrapper

import android.content.Context

class DefaultResourceWrapper(
        private val context: Context
): ResourceWrapper {

    override fun getString(res: Int): String = context.getString(res)
}