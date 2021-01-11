package com.web.exchangerate.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Parcelize
data class PrivatBankRate (
    val name: String?,
    val buy: Double?,
    val sale: Double?
):Parcelable
