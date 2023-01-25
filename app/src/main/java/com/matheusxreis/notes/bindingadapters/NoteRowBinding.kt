package com.matheusxreis.notes.bindingadapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.matheusxreis.notes.R

class NoteRowBinding {
    companion object {
        @BindingAdapter("setImportantColor")
        @JvmStatic
        fun setImportantColor(imageView: ImageView, important:Boolean){
            if(important){
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.blue_500
                    )
                )
            }
        }
    }
}