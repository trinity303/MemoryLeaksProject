package com.example.memory.leaks.project.common.wrapper

import androidx.annotation.StringRes

interface ResourceWrapper {

    fun getString(@StringRes res: Int): String
}