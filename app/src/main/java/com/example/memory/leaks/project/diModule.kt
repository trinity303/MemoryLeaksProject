package com.example.memory.leaks.project

import com.example.memory.leaks.project.sender.data.DefaultDetailsSenderRepository
import com.example.memory.leaks.project.sender.domain.DetailsSenderRepository
import com.example.memory.leaks.project.sender.domain.DetailsSenderUseCase
import com.example.memory.leaks.project.sender.presentation.DetailsSenderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author kebirova@kolesa.kz
 */
val diModule: Module = module {

    viewModel {
        DetailsSenderViewModel(
                useCase = get()
        )
    }

    factory {
        DetailsSenderUseCase(
                repository = get()
        )
    }

    single<DetailsSenderRepository> {
        DefaultDetailsSenderRepository()
    }
}