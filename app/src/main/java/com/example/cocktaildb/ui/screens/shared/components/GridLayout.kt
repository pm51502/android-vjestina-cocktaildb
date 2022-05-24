package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cocktaildb.R
import com.example.cocktaildb.utils.ItemViewState
import com.example.cocktaildb.utils.QueryType
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun GridLayout(
    modifier: Modifier = Modifier,
    title: String,
    items: List<ItemViewState>,
    onItemClick: (queryParam: String, queryType: QueryType) -> Unit
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
            items.forEach {
                ItemCard(
                    itemViewState = it,
                    onItemClick = onItemClick
                    /*painter = painterResource(id = it.painterId),
                    imageTitle = it.title,
                    queryType = it.queryType*/
                )
            }
        }
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))
    }
}
