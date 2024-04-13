package com.work.expensetracker.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryModel(
    val categoryItem: String,
    val icon: ImageVector? = null,
)