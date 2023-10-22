package com.example.realestatecatalog.data

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RealEstateProperty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    val id: Long = 0,
    val photo: String,
    val price: Double,
    val area: Double,
    val address: String,
    val rooms: Int,
    val PricePerSquareMeter: String,
    val floor: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(photo)
        parcel.writeDouble(price)
        parcel.writeDouble(area)
        parcel.writeString(address)
        parcel.writeInt(rooms)
        parcel.writeString(PricePerSquareMeter)
        parcel.writeInt(floor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RealEstateProperty> {
        override fun createFromParcel(parcel: Parcel): RealEstateProperty {
            return RealEstateProperty(parcel)
        }

        override fun newArray(size: Int): Array<RealEstateProperty?> {
            return arrayOfNulls(size)
        }
    }
}