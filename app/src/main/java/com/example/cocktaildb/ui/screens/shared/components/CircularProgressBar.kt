package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CircularProgressBar() {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = Color(0xFF0C002A)
        )
    }
}
