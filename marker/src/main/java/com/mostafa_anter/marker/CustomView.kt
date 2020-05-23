package com.mostafa_anter.marker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

/**
 * Created by mostafa_anter on 5/3/17.
 */
class CustomView(val mContext: Context) {

    // custom marker
    var mCustomMarkerView: View =
        (mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.custom_pin,
            null
        )

    private var mMakerBackground = mCustomMarkerView.findViewById<View>(R.id.custom_marker_view) as FrameLayout

    var mMarkerImageView = mCustomMarkerView.findViewById<View>(R.id.avatar_image) as ImageView

    fun setMarkerBackground(resId: Int){
        mMakerBackground.setBackgroundResource(resId)
    }
}