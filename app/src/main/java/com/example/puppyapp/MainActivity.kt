package com.example.puppyapp

import android.content.ClipData.newIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.puppyapp.data.Puppy
import com.example.puppyapp.ui.theme.PuppyAppTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.MAIN_SCREEN) {
                    composable(route = Screen.MAIN_SCREEN) {
                        MyApp(
                            navigateToProfile = {
                                val json = Uri.encode(Gson().toJson(it))
                                navController.navigate(route = "${Screen.PROFILE_SCREEN}/$json")
                            }
                        )
                    }
                    composable(
                        route = Screen.PROFILE_SCREEN + "/{puppy}",
                        arguments = listOf(
                            navArgument("puppy") {
                                type = AssetParamType()
                            }
                        )
                    ) {
                        val puppy = it.arguments?.getParcelable<Puppy>("puppy")
                        if (puppy != null) {
                            ProfileScreen(puppy = puppy)
                        } else {
                            ProfileScreen(puppy = Puppy())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToProfile: (Puppy) -> Unit) {
    Scaffold (
        content = {
            BarkHomeContent(navigateToProfile)
        }
    )
}