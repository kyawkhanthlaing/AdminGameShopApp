package org.gameshop.data.request

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password:String

)