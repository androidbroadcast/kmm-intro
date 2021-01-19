package com.example.movies.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class MoviesItem() : Parcelable{
    var id: Long = 0
    var title: String? = ""
    var overview: String? = ""
    @SerializedName("poster_path")
    var posterPath: String? = ""

    @SerializedName("release_date")
    var releaseDate:String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        overview = parcel.readString()
        posterPath = parcel.readString()
        releaseDate = parcel.readString()
    }

    fun imagePath():String  = "http://image.tmdb.org/t/p/w300${posterPath}"
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesItem> {
        override fun createFromParcel(parcel: Parcel): MoviesItem {
            return MoviesItem(parcel)
        }

        override fun newArray(size: Int): Array<MoviesItem?> {
            return arrayOfNulls(size)
        }
    }
}