package com.web.exchangerate.repository

import com.web.exchangerate.data.mapper.NBUMapper
import com.web.exchangerate.data.model.NBUBankRate
import com.web.exchangerate.remote.NBUApiService

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class NBURepository(private val api: NBUApiService) {
    suspend fun getExchangeRate(): List<NBUBankRate> {
        val rate = api.getNBURate()
        return NBUMapper.map(rate)
    }

    suspend fun getExchangeRateArchive(date: String): List<NBUBankRate> {
        val rate = api.getNBUArchiveRate(date)
        return NBUMapper.map(rate)
    }
}