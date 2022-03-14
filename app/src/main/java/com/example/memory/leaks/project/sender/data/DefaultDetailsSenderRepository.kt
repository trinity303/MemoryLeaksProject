package com.example.memory.leaks.project.sender.data

import com.example.memory.leaks.project.common.data.Response
import com.example.memory.leaks.project.sender.domain.DetailsSenderRepository
import java.lang.Exception

/**
 * @author kebirova@kolesa.kz
 */
class DefaultDetailsSenderRepository: DetailsSenderRepository {

    override fun send(): Response<Boolean, Exception> {
        Thread.sleep(5000)

        return Response.Success(true)
    }
}