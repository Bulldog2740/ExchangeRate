package com.web.exchangerate.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Parcelize
data class NBUBankRate(
    val id: Double?,
    val name: String?,
    val rate: Double?,
    val nameEng: String?,
): Parcelable

