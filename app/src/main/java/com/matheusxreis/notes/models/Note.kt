package com.matheusxreis.notes.models

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val important: Boolean = false,
)