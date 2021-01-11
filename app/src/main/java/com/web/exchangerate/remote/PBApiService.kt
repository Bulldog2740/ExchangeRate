package com.web.exchangerate.remote

import androidx.annotation.Keep
import com.web.exchangerate.data.model.MyGson
import com.web.exchangerate.data.response.PBResponse
import com.web.exchangerate.data.response.PbArchiveResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Keep
interface PBApiService {
    @GET("p24api/pubinfo?json&exchange&coursid=5")
    suspend fun getPrivatRate(): List<PBResponse>

    @GET("p24api/exchange_rates?json")
    suspend fun getPBArchiveRate(@Query("date") date: String): List<PbArchiveResponse>

    @GET("p24api/exchange_rates?json")
    suspend fun getPBArchiveRateNew(@Query("date") date: String): MyGson
}