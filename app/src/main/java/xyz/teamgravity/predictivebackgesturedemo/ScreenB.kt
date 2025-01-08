package xyz.teamgravity.predictivebackgesturedemo

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenB() {
    var sheetExpanded: Boolean by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.screen_b),
            fontSize = 42.sp
        )
        Button(
            onClick = {
                sheetExpanded = true
            }
        ) {
            Text(
                text = stringResource(R.string.open_sheet)
            )
        }
    }

    if (sheetExpanded) {
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismissRequest = {
                sheetExpanded = false
            }
        ) {
            Column {
                SheetItem(
                    icon = Icons.Default.Share,
                    label = R.string.share
                )
                SheetItem(
                    icon = Icons.Default.Edit,
                    label = R.string.edit
                )
                SheetItem(
                    icon = Icons.Default.Favorite,
                    label = R.string.favorite
                )
                SheetItem(
                    icon = Icons.Default.Delete,
                    label = R.string.delete
                )
            }
        }
    }
}

@Composable
fun SheetItem(
    icon: ImageVector,
    @StringRes label: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Text(
            text = stringResource(label)
        )
    }
}