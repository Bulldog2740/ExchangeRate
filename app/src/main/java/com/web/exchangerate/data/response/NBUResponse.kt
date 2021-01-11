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
data class NBUResponse (
    @SerializedName("r030") val id: Double,
    @SerializedName("txt") val name : String,
    @SerializedName("rate") val rate: Double,
    @SerializedName("cc") val nameEng : String,
): Parcelable