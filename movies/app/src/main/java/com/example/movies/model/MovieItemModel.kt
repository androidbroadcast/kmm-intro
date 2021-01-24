package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable
import com.example.movies.shared.data.MoviesItem

class MovieItemModel(): Parcelable {
    var id:Long = 0
    var title: String? = ""
    var overview: String? = ""
    var posterPath: String? = ""
    var releaseDate:String? = ""
    fun imagePath():String  = "http://image.tmdb.org/t/p/w300${posterPath}"

    constructor(item: MoviesItem):this() {
        this.id = item.id
        this.title = item.title
        this.overview = item.overview
        this.posterPath = item.posterPath
        this.releaseDate = item.releaseDate
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        overview = parcel.readString()
        posterPath = parcel.readString()
        releaseDate = parcel.readString()
    }

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

    companion object CREATOR : Parcelable.Creator<MovieItemModel> {
        override fun createFromParcel(parcel: Parcel): MovieItemModel {
            return MovieItemModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieItemModel?> {
            return arrayOfNulls(size)
        }
    }
}