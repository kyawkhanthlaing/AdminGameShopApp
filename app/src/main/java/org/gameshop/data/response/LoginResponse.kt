package org.gameshop.data.response

data class LoginResponse(
    val access_token: String,
    val id: Int,
    val message: String,
    val roles: List<Any>,
    val status: Boolean,
    val user: User
)