package br.com.brunobrito.covidhack.platform.extension

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader

fun ImageView.loadSGV(url: String, mActivity: AppCompatActivity){
    SvgLoader.pluck()
        .with(mActivity)
        .load(url, this)
}

