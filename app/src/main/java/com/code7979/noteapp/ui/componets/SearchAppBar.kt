package com.code7979.noteapp.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.code7979.noteapp.ui.theme.Blue500
import com.code7979.noteapp.ui.theme.IconColorOnLite

@Composable
fun SearchAppBar(
    onClickActivation: () -> Unit,
    text: String,
    onTextChange: (String) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClickActivation) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = IconColorOnLite
            )
        }
        Card(
            elevation = 16.dp,
            shape = CircleShape,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            CustomBasicTextField(
                text = text,
                onTextChange = onTextChange
            )
        }
    }
}

@Composable
fun CustomBasicTextField(
    text: String,
    onTextChange: (String) -> Unit,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(width = 5.dp, color = Blue500.copy(0.8f), shape = CircleShape)
            .height(40.dp)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier
                .background(Color.White, CircleShape)
                .fillMaxWidth(),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "image",
                        tint = IconColorOnLite
                    )
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (text == TextFieldValue("").text) {
                            Text("Search Notes")
                        }
                        innerTextField()
                    }
                }
            },
        )
    }
}