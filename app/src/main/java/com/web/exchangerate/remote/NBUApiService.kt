package com.web.exchangerate.remote

import androidx.annotation.Keep
import com.web.exchangerate.data.response.NBUResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Keep
interface NBUApiService {
    @GET("NBUStatService/v1/statdirectory/exchange?json")
    suspend fun getNBURate():List<NBUResponse>

    @GET("NBUStatService/v1/statdirectory/exchange?json")
    suspend fun getNBUArchiveRate(@Query("date") date:String):List<NBUResponse>
}