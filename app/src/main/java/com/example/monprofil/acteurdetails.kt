package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActeurDetailScreen(acteurId: Int, viewModel: MainViewModel = viewModel()) {
    // Appel pour récupérer les détails de l'acteur
    LaunchedEffect(acteurId) {
        viewModel.fetchActeurDetails(acteurId)
    }
    val acteurDetails by viewModel.acteurDetails.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails de l'Acteur") }
            )
        }
    ) { paddingValues ->
        // Affichage des détails ou message de chargement
        acteurDetails?.let { acteur ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val posterUrl = "https://image.tmdb.org/t/p/w500${acteur.profile_path}"
                Image(
                    painter = rememberImagePainter(posterUrl),
                    contentDescription = "Image de ${acteur.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Crop
                )

                // Nom de l'acteur
                Text(
                    text = acteur.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        } ?: run {

            Text(
                text = "Chargement ou acteur introuvable",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}
