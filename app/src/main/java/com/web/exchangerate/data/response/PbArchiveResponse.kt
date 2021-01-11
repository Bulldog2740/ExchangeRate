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
data class PbArchiveResponse (
    @SerializedName("currency") val name: String,
    @SerializedName("purchaseRate") val buy: Double,
    @SerializedName("saleRate") val sale: Double
): Parcelable


























