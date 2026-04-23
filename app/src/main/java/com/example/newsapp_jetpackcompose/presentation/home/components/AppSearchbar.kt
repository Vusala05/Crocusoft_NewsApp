package com.example.newsapp_jetpackcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Strings
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun AppSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val colors = LocalColors.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = BaseTheme.textStyle.t11.copy(color = colors.primaryText),
        singleLine = true,
        modifier = modifier
            .width(BaseTheme.dimens.searchbarWidth)
            .height(BaseTheme.dimens.searchbarHeight)
            .background(color = Color.Transparent, shape = RoundedCornerShape(BaseTheme.dimens.PaddingMedium))
            .border(
                1.dp,
                colors.secondaryText,
                RoundedCornerShape(BaseTheme.dimens.PaddingMedium)
            ) .padding(horizontal = 12.dp, vertical = 10.dp),
        decorationBox = { innerTextField ->

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(Strings.search),
                        style = BaseTheme.textStyle.t11,
                        color = colors.secondaryText
                    )
                }

                innerTextField()
            }
        }
    )
}