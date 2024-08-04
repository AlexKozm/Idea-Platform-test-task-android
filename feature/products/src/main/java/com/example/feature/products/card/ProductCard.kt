package com.example.feature.products.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.products.converters.LocalDateTimeConverter
import com.example.data.products.data.Product
import com.example.feature.products.R
import com.example.theme.CardColor
import com.example.theme.EditIconColor
import com.example.theme.TrashIconColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ProductCard(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    product: Product
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = CardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                text = product.name,
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.SemiBold),
                overflow = TextOverflow.Visible
            )
            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Create",
                        tint = EditIconColor
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = TrashIconColor
                    )
                }
            }
        }
        FlowRow(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy((-8).dp)
        ) {
            product.tags.forEach { tag ->
                SuggestionChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = tag) }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.in_stock),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Medium)
                )
                Text(text = product.amount.toString())
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.adding_date),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Medium)
                )
                Text(text = product.time.date.toString())
            }
        }

    }
}


@Preview
@Composable
private fun ProductCardPreview() {
    val product = Product(
        1,
        "Amazon Kindle Paperwhite asdfasdf",
        LocalDateTimeConverter().toDateTime(1633046400000),
        listOf("Телефон", "Новый", "Распродажа", "and more"),
        15
    )
    ProductCard(
        onDeleteClick = {},
        onEditClick = {},
        product = product
    )
}