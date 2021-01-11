package com.web.exchangerate.data.mapper

import com.web.exchangerate.data.model.MyGson
import com.web.exchangerate.data.model.PrivatBankRate
import com.web.exchangerate.data.response.PBResponse
import com.web.exchangerate.data.response.PbArchiveResponse
import org.jetbrains.annotations.NotNull
import java.util.*

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */

object PBArchiveMapper {

    fun map(list: List<PbArchiveResponse>): List<PrivatBankRate> {
        return list
            .map { privateArch -> mapSingle(privateArch) }.filterNotNull()
    }

    private fun mapSingle(response: PbArchiveResponse): PrivatBankRate? {
        if (response.buy == 0.0 && response.sale == 0.0)
            return null
        return PrivatBankRate(
            name = response.name,
            buy = response.buy,
            sale = response.sale
        )
    }

}