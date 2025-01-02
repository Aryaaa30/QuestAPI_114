package com.example.session12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.session12.ui.view.DestinasiDetail
import com.example.session12.ui.view.DestinasiEntry
import com.example.session12.ui.view.DestinasiHome
import com.example.session12.ui.view.DestinasiUpdate
import com.example.session12.ui.view.DetailView
import com.example.session12.ui.view.EntryMhsScreen
import com.example.session12.ui.view.HomeView
import com.example.session12.ui.view.UpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeView(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetail.routesWithArg, arguments = listOf(navArgument(DestinasiDetail.NIM) {
            type = NavType.StringType })
        ){
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailView(
                    navigateToItemUpdate = { navController.navigate("${DestinasiUpdate.route}/$nim") },
                    navigateBack = { navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(DestinasiUpdate.routesWithArg, arguments = listOf(navArgument(DestinasiDetail.NIM){
            type = NavType.StringType })
        ){
            val nim = it.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let { nim ->
                UpdateView(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}