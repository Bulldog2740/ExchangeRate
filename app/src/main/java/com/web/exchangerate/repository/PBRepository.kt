package com.web.exchangerate.repository

import com.web.exchangerate.data.mapper.PBArchiveMapper
import com.web.exchangerate.data.mapper.PBMapper
import com.web.exchangerate.data.model.PrivatBankRate
import com.web.exchangerate.remote.PBApiService


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class PBRepository(private val api: PBApiService) {
    suspend fun getExchangeRate(): List<PrivatBankRate> {
        val rate = api.getPrivatRate()
        return PBMapper.map(rate)
    }


    suspend fun getExchangeRateArchive(date: String):List<PrivatBankRate>{
        val rate = api.getPBArchiveRateNew(date)
return  PBArchiveMapper.map(rate.exchangeRate)
    }


    }