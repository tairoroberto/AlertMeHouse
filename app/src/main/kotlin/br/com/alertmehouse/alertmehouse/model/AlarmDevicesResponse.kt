package br.com.alertmehouse.alertmehouse.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by tairo on 3/25/18 12:52 PM.
 */
@Parcelize
class AlarmDevicesResponse(
        @SerializedName("data")
        val devices: List<AlarmDevice>?  = ArrayList()): Parcelable