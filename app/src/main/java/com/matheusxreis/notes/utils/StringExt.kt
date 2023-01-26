package com.matheusxreis.notes.utils

fun String.ellipsize(number:Int = 20):String{
    if(this.length>number){
        return "${this.slice(0..number-1)}..."
    }
    return this
}