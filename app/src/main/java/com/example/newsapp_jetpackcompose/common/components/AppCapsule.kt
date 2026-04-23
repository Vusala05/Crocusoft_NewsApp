package com.example.newsapp_jetpackcompose.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors
import com.example.newsapp_jetpackcompose.ui.theme.NewsApp_JetpackComposeTheme

@Composable
fun AppCapsule(
    modifier                                       : Modifier = Modifier,
    onClick : () -> Unit,
    content : @Composable RowScope.()->Unit
){
   val colors = LocalColors.current
    Surface(
        modifier = modifier,
        color = colors.secondary,
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = BaseTheme.dimens.PaddingSmall, vertical = BaseTheme.dimens.PaddingMicro)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = content,
        )
    }
}
@Preview
@Composable
fun SimpleComposablePreview() {
    NewsApp_JetpackComposeTheme {
        AppCapsule(onClick = {}) {
            Text(
                text = "Finance",
                color = Color.White
            )
        }
    }
}