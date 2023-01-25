package com.matheusxreis.notes.models

data class Note(
    private val id: Int,
    private val title: String,
    private val text: String,
    private val important: Boolean = false,
)