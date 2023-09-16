package dev.leonlatsch.avivchallange.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.leonlatsch.avivchallange.listings.view.compose.LISTINGS_SCREEN_NAV_PATH
import dev.leonlatsch.avivchallange.listings.view.compose.ListingsScreen
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AVIVChallangeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = LISTINGS_SCREEN_NAV_PATH) {
                    composable(LISTINGS_SCREEN_NAV_PATH) { ListingsScreen(navController) }
                    // TODO: Detail Screen
                }
            }
        }
    }
}