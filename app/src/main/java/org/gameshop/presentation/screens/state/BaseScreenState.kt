package org.gameshop.presentation.screens.state

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


data class BaseScreenState< T>(
    var isLoading: Boolean=false,
    var errorMessage: String="",
    var data: T?=null
)