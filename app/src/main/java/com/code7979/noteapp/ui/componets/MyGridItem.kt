package com.code7979.noteapp.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code7979.noteapp.ui.theme.IconColorOnLite

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyGridItem(
    title: String,
    content: String,
    date: String,
    padding: Dp = 6.dp,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        backgroundColor = Color(0xFFf3f6fb)
    ) {
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = content,
                textAlign = TextAlign.Start,
                maxLines = 7,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                IconButton(
                    onClick = onDelete,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = IconColorOnLite
                    )
                }
            }
        }

    }
}