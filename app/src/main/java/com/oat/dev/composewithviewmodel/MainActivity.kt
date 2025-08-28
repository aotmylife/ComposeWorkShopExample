package com.oat.dev.composewithviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oat.dev.composewithviewmodel.Screen.Screen
import com.oat.dev.composewithviewmodel.ui.theme.ComposeWithViewModelTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeWithViewModelTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding() // handle edge to edge
                ) { innerPadding ->
                    MainPage(innerPadding = innerPadding, viewModel = viewModel)
                }
            }
        }
    }
}

// simple counter without view model
@Composable
fun CounterNoViewModel(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Box(modifier.fillMaxSize()) {
        Button(
            onClick = { count++ },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Count : $count")
        }
    }
}

// simple counter with view model
@Composable
fun CounterWithViewModel(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val count by viewModel::count
    Box(modifier.fillMaxSize()) {
        Button(
            onClick = { viewModel.increase() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Count : $count")
        }
    }
}

@Composable
fun LandingScreen(
    viewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues = PaddingValues()
) {
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAPi()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(
            items = user.pullRequest,
            key = { it.id }
        ) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.Detail)
                    },
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.body,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = item.createdAT,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MainPage(innerPadding: PaddingValues = PaddingValues(), viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Main,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Screen.Main> { navEntry ->
            LandingScreen(viewModel, navController, innerPadding)
        }
        composable<Screen.Detail> { navEntry ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Detail page",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeWithViewModelTheme {
        CounterNoViewModel()
    }
}