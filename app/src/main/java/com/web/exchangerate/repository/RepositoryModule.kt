package com.web.exchangerate.repository

import org.koin.dsl.module

/**
 *Created by Yehor Kudimov on 08.01.2021.
 */
fun repositoryModule() = module {
    factory { NBURepository(get()) }
    factory { PBRepository(get()) }
}