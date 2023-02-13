package com.code7979.noteapp.ui.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.code7979.noteapp.ui.theme.Blue500
import com.code7979.noteapp.ui.theme.IconColorOnDark

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NormalAppBar(
    onClickActivation: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            elevation = 16.dp,
            backgroundColor = Blue500.copy(alpha = 0.8f),
            shape = CircleShape,
            onClick = onClickActivation
        ) {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(start = 10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "image",
                    tint = IconColorOnDark
                )
                Text(
                    text = "Search Notes",
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}