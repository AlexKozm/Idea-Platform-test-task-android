package com.example.myapplication.screens.productsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.products.data.Product
import com.example.myapplication.products.data.testData
import com.example.myapplication.ui.theme.DialogBackground
import com.example.myapplication.ui.theme.DialogContentColor

@Composable
fun EditProductAmountDialog(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit,
    productToChange: Product
) {
    var product by remember {
        mutableStateOf(productToChange)
    }
    AlertDialog(
        containerColor = DialogBackground,
        icon = {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Delete warning")
        },
        title = {
            Text(text = "Количество товара")
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val amount = product.amount - 1
                        product = product.copy(amount = amount.coerceAtLeast(0))
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_remove_circle),
                        contentDescription = "",
                        tint = DialogContentColor
                    )
                }
                Text(
                    text = product.amount.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(
                    onClick = {
                        val amount = product.amount + 1
                        product = product.copy(amount = amount)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_add_circle),
                        contentDescription = "",
                        tint = DialogContentColor
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(product.amount) }) {
                Text(
                    text = "Принять",
                    color = DialogContentColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Отмена",
                    color = DialogContentColor
                )
            }
        },
        onDismissRequest = onDismiss,
    )
}


@Preview(showBackground = true)
@Composable
private fun EditProductAmountDialogPreview() {
    EditProductAmountDialog(
        onConfirm = {},
        onDismiss = {},
        productToChange = testData[0]
    )
}