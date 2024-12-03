import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.monprofil.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieId: Int, viewModel: MainViewModel = viewModel()) {
    // Appels pour récupérer les détails du film et les crédits (casting)
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
        viewModel.fetchMovieCredits(movieId)
    }

    val movieDetails by viewModel.movieDetails.collectAsState()
    val movieCredits by viewModel.movieCredits.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails du Film", style = MaterialTheme.typography.titleMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        movieDetails?.let { movie ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // En-tête avec image
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                        Image(
                            painter = rememberImagePainter(posterUrl),
                            contentDescription = "Affiche de ${movie.title}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Black.copy(alpha = 0.5f)
                                )
                        )
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )
                    }
                }

                // Synopsis et note
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Synopsis",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = movie.overview,
                                style = MaterialTheme.typography.bodyMedium,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 6
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Note moyenne : ${movie.vote_average}/10",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                        }
                    }
                }

                // Casting
                item {
                    Text(
                        text = "Casting",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                item {
                    movieCredits?.let { credits ->
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(credits) { actor ->
                                Column(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .width(100.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val profileUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
                                    Image(
                                        painter = rememberImagePainter(profileUrl),
                                        contentDescription = "Photo de ${actor.name}",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(40.dp))
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = actor.name,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = FontWeight.Medium
                                        ),
                                        textAlign = TextAlign.Center,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    } ?: Text(
                        text = "Pas de casting disponible",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Chargement ou film introuvable",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
