package com.web.exchangerate.repository

import com.web.exchangerate.repository.NBURepository
import com.web.exchangerate.repository.PBRepository
import org.koin.dsl.module

/**
 *Created by Yehor Kudimov on 08.01.2021.
 */
fun repositoryModule() = module {
    factory { NBURepository(get()) }
    factory { PBRepository(get()) }
}