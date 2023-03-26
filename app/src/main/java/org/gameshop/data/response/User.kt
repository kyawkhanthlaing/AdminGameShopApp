package org.gameshop.data.response

data class User(
    val balance: Int,
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val owner: Boolean,
    val phone: Any,
    val photo: Any,
    val roles: List<Any>,
    val updated_at: String
)