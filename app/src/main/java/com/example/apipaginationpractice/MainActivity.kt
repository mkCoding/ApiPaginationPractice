package com.example.apipaginationpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apipaginationpractice.presentation.TopAnimeScreen
import com.example.apipaginationpractice.presentation.TopAnimeViewModel
import com.example.apipaginationpractice.ui.theme.ApiPaginationPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiPaginationPracticeTheme {
                val viewModel: TopAnimeViewModel = hiltViewModel()
                val state by viewModel.pageLoadState.collectAsState()
                val onNext = {viewModel.nextPage()}
                val onPrevious = {viewModel.previousPage()}

                TopAnimeScreen(
                    state = state,
                    onNext = onNext,
                    onPrevious = onPrevious
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiPaginationPracticeTheme {
        Greeting("Android")
    }
}