package com.bateman.bignerdranch.CriminalIntent

import java.util.*

data class Crime(
    val id: UUID,
    val title: String,
    val date: Date,
    val isSolved: Boolean
)