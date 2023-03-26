package org.gameshop.data.response

import androidx.lifecycle.LiveData

data class GenericResponse<T>(
    val message: String,
    val status: Boolean,
    val data: T
)