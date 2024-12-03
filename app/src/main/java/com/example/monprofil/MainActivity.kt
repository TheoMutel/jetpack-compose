package com.example.monprofil

import MovieDetailScreen
import SerieDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import java.lang.reflect.Modifier

@Serializable class DestinationProfile
@Serializable class DestinationFilm
@Serializable class DestinationSerie
@Serializable class DestinationActeur


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
            var isSearching by remember { mutableStateOf(false) }

            MonProfilTheme {
                Scaffold(
                    topBar = @androidx.compose.runtime.Composable {
                        if (currentDestination?.hasRoute<DestinationProfile>() == false) {
                            TopAppBar(
                                title = {
                                    if (isSearching) {
                                        TextField(
                                            value = searchQuery,
                                            onValueChange = { searchQuery = it },
                                            label = { Text("Recherche") },
                                        )
                                    }
                                },
                                actions = {
                                    // Bouton de recherche
                                    IconButton(onClick = {
                                        isSearching = !isSearching
                                        if (!isSearching) {
                                            searchQuery = TextFieldValue("") // Réinitialiser la recherche
                                        }
                                    }) {
                                        Icon(Icons.Filled.Search, contentDescription = "Rechercher")
                                    }

                                    // Bouton de clear (pour effacer la recherche)
                                    if (isSearching && searchQuery.text.isNotEmpty()) {
                                        IconButton(onClick = {
                                            searchQuery = TextFieldValue("") // Effacer le texte de recherche
                                        }) {
                                            Icon(Icons.Filled.Clear, contentDescription = "Effacer la recherche")
                                        }
                                    }
                                }
                            )
                        }
                    },


                    bottomBar = {
                        if (currentDestination?.hasRoute<DestinationProfile>() == false) {
                            NavigationBar {

                                //1er item navbar avec un label
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "Catalogue des films"
                                        )
                                    },
                                    label = { Text("Films") }, // Label sous l'icône
                                    selected = currentDestination?.hasRoute<DestinationFilm>() == true,
                                    onClick = { navController.navigate(DestinationFilm()) })

                                //2e item navbar avec un label
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.List,
                                            contentDescription = "Catalogue des séries"
                                        )
                                    },
                                    label = { Text("Séries") }, // Label sous l'icône
                                    selected = currentDestination?.hasRoute<DestinationSerie>() == true,
                                    onClick = { navController.navigate(DestinationSerie()) })

                                //3e item navbar avec un label
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = "Catalogue des acteurs"
                                        )
                                    },
                                    label = { Text("Acteurs") }, // Label sous l'icône
                                    selected = currentDestination?.hasRoute<DestinationActeur>() == true,
                                    onClick = { navController.navigate(DestinationActeur()) })

                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = DestinationProfile()) {
                        composable<DestinationProfile> {
                            ProfileScreen(navController, innerPadding, windowSizeClass)
                        }
                        composable<DestinationFilm> {
                            FilmScreen(viewModel = viewModel(), searchQuery = searchQuery.text, navController)
                        }
                        composable<DestinationSerie> {
                            SerieScreen(viewModel = viewModel(), searchQuery = searchQuery.text, navController)
                        }
                        composable<DestinationActeur> {
                            ActeurScreen(viewModel = viewModel(), searchQuery = searchQuery.text, navController)
                        }
                        composable("movie/{movieId}") { backStackEntry ->
                            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                            movieId?.let { MovieDetailScreen(movieId = it) }
                        }
                        composable("serie/{serieId}") { backStackEntry ->
                            val serieId = backStackEntry.arguments?.getString("serieId")?.toIntOrNull()
                            serieId?.let { SerieDetailScreen(serieId = it) }
                        }
                        composable("acteur/{acteurId}") { backStackEntry ->
                            val acteurId = backStackEntry.arguments?.getString("acteurId")?.toIntOrNull()
                            acteurId?.let { ActeurDetailScreen(acteurId = it) }
                        }

                    }
                }
            }
        }
    }
}