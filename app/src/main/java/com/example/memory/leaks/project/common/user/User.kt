package com.example.memory.leaks.project.common.user

import com.example.memory.leaks.project.R
import com.example.memory.leaks.project.common.wrapper.ResourceWrapper

class User private constructor(
        private val sResourceWrapper: ResourceWrapper
) {

    val userName: String
        get() = sResourceWrapper.getString(R.string.user_name)

    companion object {
        private var sInstance: User? = null

        fun createInstance(resourceWrapper: ResourceWrapper) {
            sInstance = User(resourceWrapper)
        }

        val instance: User
            get() = if (sInstance == null) {
                throw IllegalArgumentException("User cannot be null")
            } else {
                sInstance!!
            }
    }
}