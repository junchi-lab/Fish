package com.junechee.fish.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junechee.fish.presentation.component.FishButton
import com.junechee.fish.presentation.component.FishTextField
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    LoginScreen(
        id = "",
        password = "",
        onIdChange = {},
        onPasswordChange = {},
        onNavigateToSignUpScreen = viewModel::onLoginClick
    )

}

@Composable
private fun LoginScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToSignUpScreen: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Connected",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = "Your favorite social network",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "Log in",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "id",
                    style = MaterialTheme.typography.labelSmall
                )
                FishTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueChange = onIdChange,
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "password",
                    style = MaterialTheme.typography.labelSmall
                )
                FishTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = onPasswordChange,
                )

                FishButton(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    text = "Login",
                    onClick = {}
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 24.dp)
                        .clickable(onClick = onNavigateToSignUpScreen)
                ) {
                    Text(text = "Don't have an account?")
                    Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
                }

            }
        }
    }


}

@Preview
@Composable
private fun LoginScreenPreview() {
    FishTheme {
        LoginScreen(
            id = "aaa",
            password = "123",
            onIdChange = {},
            onPasswordChange = {},
            onNavigateToSignUpScreen = {}
        )
    }

}