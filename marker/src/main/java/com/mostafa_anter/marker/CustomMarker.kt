package com.mostafa_anter.marker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by mostafa_anter on 5/3/17.
 */

class CustomMarker private constructor(
    private val mContext: Context?,
    private val avatarURL: String?,
    private val googleMap: GoogleMap?,
    private val lat: Double?,
    private val long: Double?,
    private val customView: CustomView?
) {

    data class Builder(
        private var mContext: Context? = null,
        private var avatarURL: String? = null,
        private var googleMap: GoogleMap? = null,
        private var lat: Double? = null,
        private var long: Double? = null
    ) {

        private lateinit var customView: CustomView

        fun context(mContext: Context) = apply {
            this.mContext = mContext
            customView = CustomView(mContext)
        }
        fun avatar(avatarURL: String) = apply { this.avatarURL = avatarURL }
        fun googleMap(googleMap: GoogleMap) = apply { this.googleMap = googleMap }
        fun lat(lat: Double) = apply { this.lat = lat }
        fun long(long: Double) = apply { this.long = long }
        fun setMarkerBackground(resId: Int? = null) = apply {
            if (resId != null) {
                this.customView.setMarkerBackground(resId)
            }
        }

        fun build() =
            CustomMarker(mContext, avatarURL, googleMap, lat, long, customView).addCustomMarker()
    }

    private fun addCustomMarker() {
        // adding a marker with image from URL using glide image loading library
        Glide.with(mContext!!).asBitmap().load(avatarURL)
            .fitCenter()
            .transform(CircleTransform())
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    googleMap?.addMarker(
                        MarkerOptions()
                            .position(LatLng(lat!!, long!!))
                            .icon(
                                BitmapDescriptorFactory.fromBitmap(
                                    getMarkerBitmapFromView(
                                        customView!!.mCustomMarkerView,
                                        resource
                                    )
                                )
                            )
                    )
                    googleMap?.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                lat!!,
                                long!!
                            ), 15f
                        )
                    )
                }

            })
    }

    private fun getMarkerBitmapFromView(view: View, bitmap: Bitmap): Bitmap? {
        customView!!.mMarkerImageView.setImageBitmap(bitmap)
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        view.layout(0, 0, 120, 130)
        view.buildDrawingCache()
        val returnedBitmap = Bitmap.createBitmap(
            120, 130,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
        val drawable = view.background
        drawable?.draw(canvas)
        view.draw(canvas)
        return returnedBitmap
    }
}