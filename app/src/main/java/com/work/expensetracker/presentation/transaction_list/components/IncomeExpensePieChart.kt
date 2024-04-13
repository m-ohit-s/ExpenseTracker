package com.work.expensetracker.presentation.transaction_list.components

import android.graphics.Paint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.presentation.transaction_list.view_model.TransactionListViewModel

@Composable
fun IncomeExpensePieChart(
    viewModel: TransactionListViewModel,
    outerRadius: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 1000
){

    val income = viewModel.transactionsListState.value.incomeSum
    val expense = viewModel.transactionsListState.value.expenseSum
    val totalSum = income + expense
    val totalIncome = income - expense
    val incomeArc = 360 * income.toFloat()/ totalSum.toFloat()
    val expenseArc = 360 * expense.toFloat()/ totalSum.toFloat()


    var animationPlayed by remember {
        mutableStateOf(false)
    }

    var lastValue = 0f
    val textColor = MaterialTheme.colorScheme.onBackground.toArgb()

    val animateSize by animateFloatAsState(
        targetValue = if(animationPlayed) outerRadius.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val animateRotation by animateFloatAsState(
        targetValue = if(animationPlayed) 360f * 5f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(outerRadius * 2f)
                    .rotate(animateRotation)
            ) {
                  drawArc(
                      brush = Brush.linearGradient(
                          listOf(
                              Color(0xFFD3D3D3),
                              Color(0xFF1DD1A1)
                          )
                      ),
                      lastValue,
                      incomeArc,
                      useCenter = false,
                      style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                  )
                lastValue += incomeArc
                drawArc(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFFE3E3E3),
                            Color(0xFFD93965)
                        )
                    ),
                    lastValue,
                    expenseArc,
                    useCenter = false,
                    style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "$totalIncome",
                        size.width / 2,
                        size.height / 2,
                        Paint().apply {
                            textSize = 100f
                            textAlign = Paint.Align.CENTER
                            color = textColor
                        }
                    )
                }
            }
        }
    }
}