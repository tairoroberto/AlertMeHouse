package br.com.alertmehouse.alertmehouse.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by tairo on 3/25/18 1:19 PM.
 */
@Parcelize
class AlarmDevice(
        @SerializedName("name")
        var name: String = "",

        @SerializedName("id")
        var id: String = "",

        @SerializedName("status")
        var status: Boolean = false) : Parcelable