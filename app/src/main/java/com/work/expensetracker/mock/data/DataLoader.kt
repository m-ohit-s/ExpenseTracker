package com.work.expensetracker.mock.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.ui.graphics.Color
import com.work.expensetracker.core.enums.TransactionMode
import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.mock.model.CategoryMock
import com.work.expensetracker.mock.model.TransactionMock

class DataLoader {
    fun loadTransactionData(): List<TransactionMock> {
        return listOf(
            TransactionMock(
                1,
                "Netflix",
                600,
                "Streaming",
                "",
                "23-03-2024",
                "15:35",
                TransactionType.Expense,
                TransactionMode.Bank
            ),
            TransactionMock(
                2,
                "Prime",
                600,
                "Streaming",
                "",
                "21-03-2024",
                "14:35",
                TransactionType.Expense,
                TransactionMode.Bank
            ),
            TransactionMock(
                3,
                "Z-27",
                400,
                "Restaurant",
                "Date",
                "19-08-2016",
                "19:00",
                TransactionType.Expense,
                TransactionMode.Cash
            ),
            TransactionMock(
                4,
                "Salary",
                39784,
                "Salary",
                "ITT",
                "23-03-2023",
                "15:35",
                TransactionType.Income,
                TransactionMode.Bank
            )
        )
    }

    fun loadCategory(): List<CategoryMock>{
        return listOf(
            CategoryMock(
                "streaming",
                Icons.Default.Movie,
                Color.Blue
            ),
            CategoryMock(
                "food",
                Icons.Default.Fastfood,
                Color.Yellow
            ),
            CategoryMock(
                "salary",
                Icons.Default.MonetizationOn,
                Color.Green
            ),
            CategoryMock(
                "vehicle",
                Icons.Default.DirectionsCar,
                Color.Red
            ),
            CategoryMock(
                "travel",
                Icons.Default.TravelExplore,
                Color.Gray
            )
        )
    }
}