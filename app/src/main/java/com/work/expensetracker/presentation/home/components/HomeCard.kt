package com.work.expensetracker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.work.expensetracker.presentation.home.view_model.HomeScreenViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Currency

@Composable
fun HomeCard(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, end = 12.dp, start = 12.dp)) {
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(12.dp)
                    ){
                        val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yy")
                        Text(
                            text = LocalDate.now().format(formatter),
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)){
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = "Balance")
                        Text(text = "${Currency.getInstance("INR").symbol}${viewModel.income.value - viewModel.expense.value}")
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    AccountCard(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp),
                        text = "INR: ${viewModel.income.value}",
                        brush = Brush.linearGradient(listOf(Color(0xFF1DD1A1), Color(0xFFD3D3D3))),
                        isIncrease = true
                    )
                    AccountCard(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp),
                        text = "INR: ${viewModel.expense.value}",
                        brush = Brush.linearGradient(listOf(Color(0xFFD93965), Color(0xFFE3E3E3))),
                        isIncrease = false
                    )
                }

            }
        }
    }
}