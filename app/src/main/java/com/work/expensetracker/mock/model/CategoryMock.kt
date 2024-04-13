package com.work.expensetracker.mock.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryMock(
    val categoryItem: String,
    val icon: ImageVector? = null,
    val color: Color
)
