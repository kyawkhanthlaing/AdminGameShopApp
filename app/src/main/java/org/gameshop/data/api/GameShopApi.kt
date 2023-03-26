package org.gameshop.data.api

import org.gameshop.data.request.LoginRequest
import org.gameshop.data.response.GenericResponse
import org.gameshop.data.response.LoginResponse
import org.gameshop.data.response.User
import org.gameshop.data.response.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface GameShopApi {

    @POST("/api/admin/v1/login")
    suspend fun login(
        @Body
        loginRequest: LoginRequest
    ): Response<GenericResponse<UserData>>



}