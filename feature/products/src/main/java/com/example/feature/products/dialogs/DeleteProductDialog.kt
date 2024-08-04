package com.example.feature.products.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature.products.R
import com.example.theme.DialogBackground
import com.example.theme.DialogContentColor

@Composable
internal fun DeleteProductDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = DialogBackground,
        icon = {
            Icon(imageVector = Icons.Default.Warning, contentDescription = "Delete warning")
        },
        title = {
            Text(text = stringResource(R.string.products_deletion_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.product_deletion_dialog_text))
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.yes),
                    color = DialogContentColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.no),
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