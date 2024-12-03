import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.monprofil.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerieDetailScreen(serieId: Int, viewModel: MainViewModel = viewModel()) {
    // Appel pour récupérer les détails de la série
    LaunchedEffect(serieId) {
        viewModel.fetchSerieDetails(serieId)
    }

    // Observer l'état des détails de la série
    val serieDetails by viewModel.serieDetails.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails de la Série") }
            )
        }
    ) { paddingValues ->
        serieDetails?.let { serie ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Affichage de l'image de la série
                item {
                    val posterUrl = "https://image.tmdb.org/t/p/w500${serie.poster_path}"
                    Image(
                        painter = rememberImagePainter(posterUrl),
                        contentDescription = "Affiche de ${serie.name}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .padding(bottom = 16.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Titre et synopsis
                item {
                    Text(
                        text = serie.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = serie.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        } ?: run {
            Text(
                text = "Chargement ou série introuvable",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}
