package com.arina.myjetpackapp.ui.nav

sealed class Navigation(val route: String){
    object Home: Navigation("home")
    object Profile: Navigation("profile")
    object Detail: Navigation("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}