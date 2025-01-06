package com.junechee.fish.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.name
    ) {
        composable(route = LoginRoute.WelcomeScreen.name) {
            WelcomeScreen(
                onNavigationToLoginScreen = { navController.navigate(route = LoginRoute.LoginScreen.name) }
            )
        }
        composable(route = LoginRoute.LoginScreen.name) {
            LoginScreen()
        }
        composable(route = LoginRoute.SignUpScreen.name) {
            SignUpScreen(
                id = "aaaaaa",
                username = "bbbbb",
                password1 = "12345",
                password2 = "12345",
                onIdChange = {},
                onUsernameChange = {},
                onPassword1Change = {},
                onPassword2Change = {}
            ) { }
        }
    }


}