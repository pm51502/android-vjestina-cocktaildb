package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktaildb.utils.CategoryViewState
import com.example.cocktaildb.utils.QueryType

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: CategoryViewState,
    onCategoryClick: (queryParam: String, queryType: QueryType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .clickable {
                onCategoryClick.invoke(category.query, category.queryType)
            }
        //.padding(15.dp)
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = modifier.height(200.dp)) {
                Image(
                    painter = painterResource(id = category.painterId),
                    contentDescription = "Image card",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .matchParentSize()
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = size.height / 3,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    gradient,
                                    blendMode = BlendMode.Multiply
                                )
                            }
                        }
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = category.categoryName,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
    }
}
