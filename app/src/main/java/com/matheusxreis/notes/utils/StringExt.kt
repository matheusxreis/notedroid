package com.matheusxreis.notes.utils

fun String.ellipsize():String{
    if(this.length>20){
        return "${this.slice(0..19)}..."
    }
    return this
}