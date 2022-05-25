package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cocktaildb.R
import com.example.cocktaildb.utils.CategoryViewState
import com.example.cocktaildb.utils.QueryType
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun CategoriesLayout(
    modifier: Modifier = Modifier,
    title: String,
    categories: List<CategoryViewState>,
    onCategoryClick: (queryParam: String, queryType: QueryType) -> Unit
) {
    Column {
        ContentTitle(text = title)

        FlowRow(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_md),
                top = dimensionResource(id = R.dimen.padding_sm)
            ),
            mainAxisSize = SizeMode.Wrap,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            mainAxisSpacing = dimensionResource(id = R.dimen.padding_md),
            crossAxisSpacing = dimensionResource(id = R.dimen.padding_md)
        ) {
            categories.forEach {
                CategoryCard(
                    category = it,
                    onCategoryClick = onCategoryClick
                )
            }
        }
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))
    }
}
