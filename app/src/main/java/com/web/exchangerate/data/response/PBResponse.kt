package com.web.exchangerate.data.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
@Parcelize
@Keep
data class PBResponse (
    @SerializedName("ccy") val name: String,
    @SerializedName("buy") val buy: Double,
    @SerializedName("sale") val sale: Double
):Parcelable
