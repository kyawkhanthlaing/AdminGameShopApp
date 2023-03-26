package org.gameshop.presentation.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gameshop.domain.model.UserModel
import org.gameshop.domain.usecase.LoginUseCase
import org.gameshop.presentation.screens.state.BaseScreenState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginState:MutableStateFlow<BaseScreenState<UserModel>> = MutableStateFlow(BaseScreenState())

    fun login(email:String,password:String) {
        viewModelScope.launch {
            loginState.update {
                it.copy(isLoading = true)
            }
            loginUseCase(
                loginPayload = LoginUseCase.LoginPayload(email, password)
            ).collectLatest {
                it.onSuccess {userModel->
                    Log.d("user",userModel.toString())
                    loginState.update {
                        it.copy(isLoading = false, data = userModel)
                    }
                }.onFailure {e->
                    loginState.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Something was wrong!")
                    }

                }
            }
        }
    }
}