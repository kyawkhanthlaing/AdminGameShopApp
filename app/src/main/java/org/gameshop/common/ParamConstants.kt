package org.gameshop.common

import androidx.annotation.StringRes
import org.gameshop.R

enum class MainRoute(@StringRes title: Int) {
    LOGIN(title = R.string.login_screen),
    Home(title = R.string.home_screen),
}

enum class HomeRoute(@StringRes title: Int){
    DASHBOARD(title = R.string.dashboard_screen),
    ORDER_MANAGEMENT(title = R.string.order_management_screen),
    LOGOUT(title = R.string.Logout)
}


