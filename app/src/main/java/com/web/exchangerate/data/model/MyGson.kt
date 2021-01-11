package com.web.exchangerate.data.model

import android.os.Parcelable
import com.web.exchangerate.data.response.PbArchiveResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyGson(
    val exchangeRate: List<PbArchiveResponse>
):Parcelable