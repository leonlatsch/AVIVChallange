package dev.leonlatsch.avivchallange.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.leonlatsch.avivchallange.listings.view.compose.LISTINGS_SCREEN_NAV_PATH
import dev.leonlatsch.avivchallange.listings.view.compose.ListingsScreen
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ARG_LISTING_DETAIL
import dev.leonlatsch.avivchallange.listings.view.detail.compose.LISTING_DETAIL_SCREEN_NAV_PATH
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreen
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AVIVChallangeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = LISTINGS_SCREEN_NAV_PATH) {
                    composable(LISTINGS_SCREEN_NAV_PATH) { ListingsScreen(navController) }
                    composable(
                        LISTING_DETAIL_SCREEN_NAV_PATH,
                        listOf(navArgument(ARG_LISTING_DETAIL) { type = NavType.IntType })
                    ) {
                        ListingDetailScreen(navController)
                    }
                }
            }
        }
    }
}