package org.gameshop.domain.usecase

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gameshop.data.api.GameShopApi
import org.gameshop.data.request.LoginRequest
import org.gameshop.data.response.GenericResponse
import org.gameshop.data.response.LoginResponse
import org.gameshop.data.response.User
import org.gameshop.data.response.UserData
import org.gameshop.domain.model.UserModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val api: GameShopApi
) :
    BaseUseCase<LoginUseCase.LoginPayload, UserModel>() {
    override suspend operator fun invoke(loginPayload: LoginPayload): Flow<Result<UserModel>> {
        return handleApiCall<LoginPayload,LoginRequest,UserData,UserModel>(
            param = loginPayload,
            requestMapper = {
                LoginRequest(it.email,it.password)
            },
            apiCall ={
                api.login(it)
            },
            responseMapper = {
                UserModel(it.user.email,it.user.name)
            }
        )
    }

    data class LoginPayload(
        val email: String,
        val password: String
    )




}


