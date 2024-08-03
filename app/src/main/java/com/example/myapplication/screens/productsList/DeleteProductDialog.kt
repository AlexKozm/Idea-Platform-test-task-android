package com.example.myapplication.screens.productsList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.DialogBackground
import com.example.myapplication.ui.theme.DialogContentColor

@Composable
fun DeleteProductDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = DialogBackground,
        icon = {
            Icon(imageVector = Icons.Default.Warning, contentDescription = "Delete warning")
        },
        title = {
            Text(text = "Удаление товара")
        },
        text = {
            Text(text = "Вы действительно хотите удалить выбранный товар?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = "Да",
                    color = DialogContentColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Нет",
                    color = DialogContentColor,
                )
            }
        },
        onDismissRequest = onDismiss,
    )
}


@Preview(showBackground = true)
@Composable
private fun DeleteProductDialogPreview() {
    DeleteProductDialog(
        onConfirm = {},
        onDismiss = {}
    )
}