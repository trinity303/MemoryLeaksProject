package com.example.memory.leaks.project.main.domain

import com.example.memory.leaks.project.common.wrapper.ResourceWrapper

/**
 * @author kebirova@kolesa.kz
 */
interface LoggerInitializer {

    fun initialize(resourceWrapper: ResourceWrapper)
}