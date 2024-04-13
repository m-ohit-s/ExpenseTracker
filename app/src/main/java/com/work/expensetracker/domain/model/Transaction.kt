package com.work.expensetracker.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.TravelExplore
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val amount: Double,
    val type: String,
    val timestamp: Long,
    val paymentMode: String,
    val category: String
){
    companion object{
        val categories = listOf(
            CategoryModel(
                "streaming",
                Icons.Default.Movie,
            ),
            CategoryModel(
                "food",
                Icons.Default.Fastfood,
            ),
            CategoryModel(
                "salary",
                Icons.Default.MonetizationOn,
            ),
            CategoryModel(
                "vehicle",
                Icons.Default.DirectionsCar,
            ),
            CategoryModel(
                "travel",
                Icons.Default.TravelExplore,
            )
        )
    }
}

class InvalidTransactionException(message: String): Exception(message)
