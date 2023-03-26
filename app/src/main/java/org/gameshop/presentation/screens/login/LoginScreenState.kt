package org.gameshop.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class LoginScreenState(
    val data: Data=Data(),
    val delegate: Delegate=Delegate()
){
   data class Data(
       val isSuccess:State<Boolean> = mutableStateOf(false)
   )

    data class Delegate(
        val onClickLogin: (String,String)->Unit={
            a,b->
        }
    )
}
