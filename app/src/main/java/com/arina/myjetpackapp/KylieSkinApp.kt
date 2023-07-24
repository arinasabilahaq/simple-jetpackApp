package com.arina.myjetpackapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arina.myjetpackapp.ui.nav.Navigation
import com.arina.myjetpackapp.ui.page.detail.DetailPage
import com.arina.myjetpackapp.ui.page.home.HomePage
import com.arina.myjetpackapp.ui.page.profile.ProfilePage
import com.arina.myjetpackapp.ui.theme.MyJetpackAppTheme

@Composable
fun KylieSkinApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Navigation.Home.route) {
                TopBar(navController = navController, modifier = modifier.padding(start = 8.dp))
            }
        }
    ) {padding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            modifier = Modifier.padding(padding)
        ){
            composable(Navigation.Home.route) {
                HomePage(navigateToDetail = { id ->
                    navController.navigate(Navigation.Detail.createRoute(id))
                })
            }
            composable(route = Navigation.Detail.route, arguments = listOf(navArgument("id") { type = NavType.LongType })) {
                val id = it.arguments?.getLong("id") ?: -1L
                DetailPage(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    })
            }
            composable(route = Navigation.Profile.route) {
                ProfilePage(onBackClick = { navController.navigateUp() })
            }
        }
    }
}

@Composable
fun TopBar(navController: NavHostController, modifier: Modifier) {
    TopAppBar(
        backgroundColor = Color(android.graphics.Color.parseColor("#FFD4D4"))
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(0.8f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "KylieSkin App",
                fontSize = 24.sp,
                color = Color.White,
            )
        }
        IconButton(
            onClick = {
                navController.navigate(Navigation.Profile.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "about_page",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KylieSkinAppPreview() {
    MyJetpackAppTheme {
        KylieSkinApp()
    }
}