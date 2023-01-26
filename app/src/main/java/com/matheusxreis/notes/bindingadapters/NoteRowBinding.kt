package com.matheusxreis.notes.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.matheusxreis.notes.R
import com.matheusxreis.notes.utils.ellipsize

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


        @BindingAdapter("ellipsizeTitle")
        @JvmStatic
        fun ellipsizeTitle(textView: TextView, title:String){
            textView.text = title.ellipsize()
        }
    }
}