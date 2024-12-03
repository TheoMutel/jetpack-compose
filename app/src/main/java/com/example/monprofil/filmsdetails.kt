import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.monprofil.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieId: Int, viewModel: MainViewModel = viewModel()) {
    // Appel pour récupérer les détails du film
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }


    val movieDetails by viewModel.movieDetails.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails du Film") }
            )
        }
    ) { paddingValues ->
        movieDetails?.let { movie ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                item {
                    val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                    Image(
                        painter = rememberImagePainter(posterUrl),
                        contentDescription = "Affiche de ${movie.title}",
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
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }


                item {
                    Text(
                        text = "Note moyenne : ${movie.vote_average}/10",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        } ?: run {
            Text(
                text = "Chargement ou film introuvable",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}
