package com.example.apipaginationpractice.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.apipaginationpractice.R
import com.example.apipaginationpractice.data.model.DataModel
import com.example.apipaginationpractice.data.model.ImagesModel
import com.example.apipaginationpractice.data.model.JpgModel
import com.example.apipaginationpractice.data.model.TopAnimeModel

@Composable
fun TopAnimeScreen(
    state: PageLoadState,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding() // set ideal padding for phone built in nav bar
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "All Anime",
            modifier = Modifier.padding(16.dp),
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold
        )

        when (state) {
            is PageLoadState.Loading ->{

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(100.dp),
                        color = Color.Blue,
                        strokeWidth = 8.dp
                    )

                }

            }
            is PageLoadState.Success -> {
                val listOfAnime = state.fullModel.data.orEmpty()

                AnimeList(
                    animeList = listOfAnime,
                    onNext = onNext,
                    onPrevious = onPrevious
                )
            }

            is PageLoadState.Error -> {
                Text(state.message)
            }
        }
    }
}

@Composable
fun AnimeList(
    animeList: List<DataModel>,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(animeList) { anime ->

                Box(
                    modifier = Modifier.wrapContentSize()
                        .padding(16.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ){
                    Column() {
                        AnimeRow(anime)
                        // Add image as well
                        AsyncImage(
                            model = anime.images?.jpg?.image_url,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                // .height(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )


                    }


                }


//                HorizontalDivider(
//                    modifier = Modifier
//                        .padding(16.dp),
//                    thickness = 3.dp,
//                    color = Color.Black
//
//                )


            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Previous Button
            Button(
                onClick = onPrevious,
                enabled = true,
                shape = RoundedCornerShape(8.dp)

            ) {
                Text("Previous")
            }

            // Next Button
            Button(
                onClick = onNext,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Next")
            }


        }
    }

}

@Composable
fun AnimeRow(anime: DataModel) {
    Text(
        text = anime.title ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        fontSize = 25.sp,
        fontWeight = FontWeight.Light
    )
}

@Preview(showBackground = true)
@Composable
fun TopAnimeScreenPreview() {
    TopAnimeScreen(
        state = PageLoadState.Success(
            TopAnimeModel(listOf(
                DataModel(title = "Pokemon", images = ImagesModel(jpg = JpgModel(
                    image_url = (R.drawable.ic_launcher_foreground).toString()

                ))),
                DataModel(title = "Pokemon"),
                DataModel(title = "Pokemon"),
                DataModel(title = "Pokemon")
            ))
        ),
        onNext = {},
        onPrevious = {}
    )
}