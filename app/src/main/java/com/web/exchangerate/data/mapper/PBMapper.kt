package com.web.exchangerate.data.mapper

import com.web.exchangerate.data.model.PrivatBankRate
import com.web.exchangerate.data.response.PBResponse

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
object PBMapper {
    fun map(response: List<PBResponse>): List<PrivatBankRate> {
        return response
            .map { private -> mapSingle(private) }
    }

    private fun mapSingle(response: PBResponse): PrivatBankRate {
        return PrivatBankRate(
            name = response.name,
            buy = response.buy,
            sale = response.sale
        )
    }
}