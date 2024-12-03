package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ProfileScreen(navController : NavController, innerPadding: PaddingValues, windowClass: WindowSizeClass) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MonImage()
                Texte()
                Texte2()
                Reseaux()
                Demarrer(navController)
            }
        }
        else -> {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Colonne gauche
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        MonImage()
                        Texte()
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            Texte2()
                            Reseaux()

                        }


                        Demarrer(navController)
                    }
                }
            }
        }
    }
}


@Composable
fun Texte(){
    Text(
        text = " Théo Mutel",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(10.dp)

    )
}
@Composable
fun MonImage(){
    Image(
        painterResource(id = R.drawable.profile),
        contentDescription = "Théo Mutel",

        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )

}
@Composable
fun Texte2(){
    Text(
        text = " Étudiant a l'IUT de Castres en 3ème année.",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(10.dp)

    )
}
@Composable
fun Reseaux() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "theo.mutel.empro@gmail.com",
                style = MaterialTheme.typography.labelSmall
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "www.linkedin.com/in/TheoMutel",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}



@Composable
fun Demarrer(navController: NavController) {
    Button(
        onClick = { navController.navigate(Destinationmusique()) },
        modifier = Modifier.padding(ButtonDefaults.IconSize)
    ) {
        Text(
            text = "Démarrer"
        )
    }
}