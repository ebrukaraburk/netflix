package com.example.netflix.uix.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.netflix.data.entity.Netflix
import com.google.gson.Gson
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val filmlerListesi = remember { mutableStateListOf<Netflix>() }
    val esaretListesi = remember { mutableStateListOf<Netflix>() }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {

        val f1 = Netflix(1, "Anna", "anna", 240)
        val f2 = Netflix(2, "Birdbox", "birdbox", 150)
        val f3 = Netflix(3, "Friends", "friends", 130)
        val f4 = Netflix(4, "Patron Bebek", "patronbebek", 100)
        val f5 = Netflix(5, "Sweethome", "sweethome", 140)
        val f6 = Netflix(6, "Wednesday", "wednesday", 240)
        val f7 = Netflix(7, "You", "you", 240)

        filmlerListesi.addAll(listOf(f1, f2, f3, f4, f5, f6, f7))


        val e1 = Netflix(8, "Esaret", "esaret", 250)
        val e2 = Netflix(9, "Ayla", "ayla", 200)
        val e3 = Netflix(10, "Forgotton", "forgotton", 180)
        val e4 = Netflix(11, "Pandora", "pandora", 220)

        esaretListesi.addAll(listOf(e1, e2, e3, e4))
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Netflix") }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            // Diziler Listesi
            Text(
                text = "En Çok İzlenen Diziler",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(filmlerListesi) { film ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(250.dp)
                            .height(200.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val filmJson = Gson().toJson(film)
                                    navController.navigate("detaySayfa/$filmJson")
                                }
                        ) {
                            val activity = (LocalContext.current as Activity)
                            Image(
                                bitmap = ImageBitmap.imageResource(
                                    id = activity.resources.getIdentifier(
                                        film.resim, "drawable", activity.packageName
                                    )
                                ),
                                contentDescription = film.ad,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Text(
                                text = film.ad,
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(message = "${film.ad} sepete eklendi")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(48.dp)
                            ) {
                                Text(text = "Sepete Ekle")
                            }
                        }
                    }
                }
            }

            // Filmler Listesi
            Text(
                text = "En Çok İzlenen Filmler",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(esaretListesi) { film ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(250.dp) // Kart genişliği
                            .height(200.dp), // Kart yüksekliği
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val filmJson = Gson().toJson(film)
                                    navController.navigate("detaySayfa/$filmJson")
                                }
                        ) {
                            val activity = (LocalContext.current as Activity)
                            Image(
                                bitmap = ImageBitmap.imageResource(
                                    id = activity.resources.getIdentifier(
                                        film.resim, "drawable", activity.packageName
                                    )
                                ),
                                contentDescription = film.ad,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Text(
                                text = film.ad,
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(message = "${film.ad} sepete eklendi")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(48.dp)
                            ) {
                                Text(text = "Sepete Ekle")
                            }
                        }
                    }
                }
            }
        }
    }
}
