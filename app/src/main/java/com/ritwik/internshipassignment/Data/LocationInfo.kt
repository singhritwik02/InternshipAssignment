package com.ritwik.internshipassignment.Data

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
 class LocationInfo(var locationTitle:String,var coOrdinates:LatLng) :Parcelable {

    companion object : Parceler<LocationInfo> {

        override fun LocationInfo.write(p0: Parcel, p1: Int) {
            p0?.let {parcel->
                parcel.writeString(locationTitle)
                parcel.writeDouble(coOrdinates.latitude)
                parcel.writeDouble(coOrdinates.longitude)

            }
        }

        override fun create(parcel: Parcel): LocationInfo {
            val title = parcel.readString()
            val lat = parcel.readDouble()
            val long = parcel.readDouble()

            val info = title?.let { LocationInfo(it, LatLng(lat,long)) }
            return LocationInfo(info!!.locationTitle,info.coOrdinates)
        }
    }
}