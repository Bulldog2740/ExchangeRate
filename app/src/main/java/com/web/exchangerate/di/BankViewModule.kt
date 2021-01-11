package com.web.exchangerate.di

import com.web.exchangerate.presentation.viewmodel.BankViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *Created by Yehor Kudimov on 08.01.2021.
 */
fun bankViewModule() = module {
    viewModel {
        BankViewModel(
            application = androidApplication(),
            pbRepository = get(),
            nbuRepository = get()
        )
    }
}