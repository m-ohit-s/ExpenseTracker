package com.work.expensetracker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    brush: Brush,
    text: String = "",
    isIncrease: Boolean = true
) {
    Card(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush),
            contentAlignment = Alignment.Center
        ){
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                if(isIncrease)
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.DarkGray)
                else
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.DarkGray)
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}