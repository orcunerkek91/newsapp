package com.orcunerkek.newsapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceModel() : Parcelable {


    @SerializedName("id")
    @Expose
    var id: String? = null


    @SerializedName("name")
    @Expose
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SourceModel> {
        override fun createFromParcel(parcel: Parcel): SourceModel {
            return SourceModel(parcel)
        }

        override fun newArray(size: Int): Array<SourceModel?> {
            return arrayOfNulls(size)
        }
    }


}