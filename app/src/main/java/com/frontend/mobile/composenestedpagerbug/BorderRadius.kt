package com.frontend.mobile.composenestedpagerbug

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BorderRadiusValuesImpl(
    val topStart: Dp,
    val topEnd: Dp,
    val bottomEnd: Dp,
    val bottomStart: Dp,
)

@Suppress("FunctionName")
fun BorderRadiusValues(
    all: Dp,
): BorderRadiusValuesImpl {
    return BorderRadiusValuesImpl(
        topStart = all,
        topEnd = all,
        bottomEnd = all,
        bottomStart = all,
    )
}

@Suppress("FunctionName")
fun BorderRadiusValues(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
): BorderRadiusValuesImpl {
    return BorderRadiusValuesImpl(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
    )
}

@Suppress("FunctionName")
fun BorderRadiusShape(
    all: Dp
): Shape {
    return BorderRadiusShape(BorderRadiusValues(all))
}

@Suppress("FunctionName")
fun BorderRadiusShape(
    values: BorderRadiusValuesImpl
): Shape {
    return RoundedCornerShape(
        topStart = values.topStart,
        topEnd = values.topEnd,
        bottomEnd = values.bottomEnd,
        bottomStart = values.bottomStart,
    )
}


fun Modifier.borderRadius(
    values: BorderRadiusValuesImpl
): Modifier = clip(
    BorderRadiusShape(values)
)


fun Modifier.borderRadius(all: Dp): Modifier = borderRadius(
    BorderRadiusValues(
        all = all,
    )
)

fun Modifier.borderRadius(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp
): Modifier = borderRadius(
    BorderRadiusValues(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
    )
)
