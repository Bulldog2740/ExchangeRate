package com.web.exchangerate.data.mapper

import com.web.exchangerate.data.model.NBUBankRate
import com.web.exchangerate.data.response.NBUResponse

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
object NBUMapper {
    fun map(response: List<NBUResponse>): List<NBUBankRate> {
        return response
            .map { nbu -> mapSingle(nbu) }
    }

    private fun mapSingle(response: NBUResponse): NBUBankRate {
        return NBUBankRate(
            id = response.id,
            name = response.name,
            nameEng = response.nameEng,
            rate = response.rate,
        )
    }
}