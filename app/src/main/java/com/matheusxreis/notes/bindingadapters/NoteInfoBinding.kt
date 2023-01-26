package com.matheusxreis.notes.bindingadapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.matheusxreis.notes.R

class NoteInfoBinding {

    companion object {
        @BindingAdapter("setImportantColorInfo")
        @JvmStatic
        fun setImportantColorInfo(imageView: ImageView, important:Boolean){
            if(important){
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.blue_500
                    )
                )
            }else {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        android.R.color.darker_gray
                    )
                )
            }
        }

    }
}