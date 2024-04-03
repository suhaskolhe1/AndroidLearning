package com.example.amphibians.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.AmphibianPhoto

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(Modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansLazyList(
            amphibians = amphibiansUiState.photos,
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        )

        is AmphibiansUiState.Error -> ErrorScreen(retryAction, Modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Loading..")
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Unable to Load Data")
        Image(painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = null)
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun AmphibiansLazyList(amphibians: List<AmphibianPhoto>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianPhotoCard(ampPhoto = amphibian, Modifier.padding(8.dp))
        }
    }
}

@Composable
fun AmphibianPhotoCard(ampPhoto: AmphibianPhoto, modifier: Modifier = Modifier) {
    Card(modifier) {
        Text(
            text = "${ampPhoto.name} (${ampPhoto.type})",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)

        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(ampPhoto.imgSrc)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = ampPhoto.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AmpCard() {
    AmphibianPhotoCard(
        ampPhoto = AmphibianPhoto(
            name = "TODOOOODODOD",
            type = "Hello",
            imgSrc = "",
            description = "This is log dsndgk dgh   jj d sdiohf f ddofhdh sdfog gsdg ",

            ),
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen({}, Modifier.fillMaxSize())
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(modifier = Modifier.fillMaxSize())
}