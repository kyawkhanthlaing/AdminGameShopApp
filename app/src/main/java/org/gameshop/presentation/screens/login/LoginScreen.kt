package org.gameshop.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.gameshop.R
import org.gameshop.common.MainRoute

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.loginState.collectLatest {
            if (it.data != null) {
                onLoginClick()
            }
        }
    }

    LoginScreenStateLess(
        state = LoginScreenState(
            data = LoginScreenState.Data(

            ),
            delegate = LoginScreenState.Delegate(
                onClickLogin = viewModel::login
            )
        )
    )

}

@Composable
fun LoginScreenStateLess(
    state: LoginScreenState
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome To Game Shop",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        CustomTextField(email = email) {
            email = it
        }
        CustomPasswordField(password = password) {
            password = it
        }
        Button(
            onClick = {
                state.delegate.onClickLogin(email, password)
            }, colors = ButtonDefaults.buttonColors(
                Color(0xFF495E57)
            )
        ) {
            Text(
                text = "Login", color = Color(0xFFEDEFEE)
            )
        }
    }
}


@Composable
fun CustomTextField(email: String, onValueChange: (String) -> Unit) {

    OutlinedTextField(
        value = email,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "email_icon",
                modifier = Modifier.size(24.dp)
            )
        },
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.textFieldColors(),
        keyboardActions = KeyboardActions.Default,
        label = {
            Text(text = "email")
        }

    )
}

@Composable
fun CustomPasswordField(password: String, onValueChange: (String) -> Unit) {

    var isVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = password,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_password),
                contentDescription = "password_icon",
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = if (isVisible) R.drawable.view else R.drawable.hide),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isVisible = !isVisible
                    }
            )
        },
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.textFieldColors(

        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions.Default,
        label = {
            Text(text = "password")
        }

    )
}


