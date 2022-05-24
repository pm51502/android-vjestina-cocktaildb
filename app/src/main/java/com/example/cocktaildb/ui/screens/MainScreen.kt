package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cocktaildb.ui.navigation.BottomBarNavHost
import com.example.cocktaildb.ui.navigation.BottomBarScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.TopBar
import com.example.cocktaildb.R

@Composable
fun MainScreen(
    rootNavController: NavController,
    bottomBarNavController: NavController
) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favorites,
    )

    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                currentRoute = currentDestination?.route,
                rootNavController = rootNavController
            )
        },

        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.primary
            ) {
                items.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    BottomNavigationItem(
                        icon = { BottomNavigationIcon(screen = screen, isSelected = selected) },
                        label = { Text(text = stringResource(screen.resourceId)) },
                        selected = selected,
                        onClick = {
                            navigateToScreen(
                                navController = bottomBarNavController,
                                route = screen.route
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomBarNavHost(
                rootNavController = rootNavController,
                bottomBarNavController = bottomBarNavController
            )
        }
    }
}

@Composable
fun BottomNavigationIcon(screen: BottomBarScreen, isSelected: Boolean) {
    val painter =
        if (screen.route == BottomBarScreen.Home.route)
            painterResource(id = R.drawable.ic_home_outline)
        else
            painterResource(id = R.drawable.ic_fav_outline)

    val selectedPainter =
        if (screen.route == BottomBarScreen.Home.route)
            painterResource(id = R.drawable.ic_home)
        else
            painterResource(id = R.drawable.ic_fav)

    if (isSelected) Icon(
        painter = selectedPainter,
        contentDescription = stringResource(id = R.string.bottom_navigation_icon_selected)
    ) else Icon(
        painter = painter,
        contentDescription = stringResource(id = R.string.bottom_navigation_icon)
    )
}
