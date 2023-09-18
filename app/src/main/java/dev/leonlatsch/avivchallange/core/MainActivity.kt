package dev.leonlatsch.avivchallange.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.leonlatsch.avivchallange.listings.view.list.compose.ListingsScreen
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreen
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreenNavRoute
import dev.leonlatsch.avivchallange.listings.view.list.compose.ListingsScreenNavRoute
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AVIVChallangeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = ListingsScreenNavRoute.route) {
                    composable(ListingsScreenNavRoute.route) { ListingsScreen(navController) }
                    composable(
                        ListingDetailScreenNavRoute.route,
                        listOf(navArgument(ListingDetailScreenNavRoute.ARG_LISTING_DETAIL_ID) { type = NavType.IntType })
                    ) {
                        ListingDetailScreen(navController)
                    }
                }
            }
        }
    }
}