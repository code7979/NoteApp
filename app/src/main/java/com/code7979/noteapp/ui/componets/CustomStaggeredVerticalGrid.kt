package com.code7979.noteapp.ui.componets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun CustomStaggeredVerticalGrid(
    // on below line we are specifying
    // parameters as modifier, num of columns
    modifier: Modifier = Modifier,
    numColumns: Int = 2,
    content: @Composable () -> Unit
) {
    // inside this grid we are creating
    // a layout on below line.
    Layout(
        // on below line we are specifying
        // content for our layout.
        content = content,
        // on below line we are adding modifier.
        modifier = modifier
    ) { measurable, constraints ->
        // on below line we are creating a variable for our column width.
        val columnWidth = (constraints.maxWidth / numColumns)

        // on the below line we are creating and initializing our items constraint widget.
        val itemConstraints = constraints.copy(maxWidth = columnWidth)

        // on below line we are creating and initializing our column height
        val columnHeights = IntArray(numColumns) { 0 }

        // on below line we are creating and initializing placeable
        val placeable = measurable.map { measurable ->
            // inside placeable we are creating
            // variables as column and placeable.
            val column = testColumn(columnHeights)
            val placeable = measurable.measure(itemConstraints)

            // on below line we are increasing our column height/
            columnHeights[column] += placeable.height
            placeable
        }

        // on below line we are creating a variable for
        // our height and specifying height for it.
        val height =
            columnHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
                ?: constraints.minHeight

        // on below line we are specifying height and width for our layout.
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // on below line we are creating a variable for column y pointer.
            val columnYPointers = IntArray(numColumns) { 0 }

            // on below line we are setting x and y for each placeable item
            placeable.forEach { placeable ->
                // on below line we are calling test
                // column method to get our column index
                val column = testColumn(columnYPointers)

                placeable.place(
                    x = columnWidth * column,
                    y = columnYPointers[column]
                )

                // on below line we are setting
                // column y pointer and incrementing it.
                columnYPointers[column] += placeable.height
            }
        }
    }
}

// on below line we are creating a test column method for setting height.
private fun testColumn(columnHeights: IntArray): Int {
    // on below line we are creating a variable for min height.
    var minHeight = Int.MAX_VALUE

    // on below line we are creating a variable for column index.
    var columnIndex = 0

    // on below line we are setting column  height for each index.
    columnHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            columnIndex = index
        }
    }
    // at last we are returning our column index.
    return columnIndex
}