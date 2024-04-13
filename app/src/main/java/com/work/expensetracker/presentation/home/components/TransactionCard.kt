package com.work.expensetracker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.domain.model.Transaction
import java.text.DateFormat
import java.util.Date

@Composable
fun TransactionCard(modifier: Modifier = Modifier, transaction: Transaction) {
    Card(modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)){
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Start) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = DateFormat.getDateInstance().format(Date(transaction.timestamp)),
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(MaterialTheme.colorScheme.tertiary)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = transaction.category,
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = transaction.paymentMode,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.Start) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp), contentAlignment = Alignment.Center){
                    Icon(imageVector = Icons.Default.AccountBalance, contentDescription = null)
                }
                Column {
                    Text(text = transaction.title)
                    Text(text = "INR: ${transaction.amount}", color = if(transaction.type == TransactionType.Income.toString()) Color(0xFF228B22) else Color(0xFFD93965))
                }
            }
        }
    }
}